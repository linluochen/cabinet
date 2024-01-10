<template>
  <div class="app-container">
    <el-form v-show="showSearch" ref="queryForm" :inline="true" :model="queryParams" label-width="100px" size="small">
      <el-form-item label="微信凭证ID" prop="appid">
        <el-input
          v-model="queryParams.appid"
          clearable
          placeholder="请输入微信凭证ID"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="微信凭证密钥" prop="secret">
        <el-input
          v-model="queryParams.secret"
          clearable
          placeholder="请输入微信凭证密钥"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="微信类型" prop="secret">
        <el-select v-model="queryParams.type" placeholder="请选择类型" style="width: 150px;">
          <el-option label="微信" value="1">微信</el-option>
          <el-option label="企业微信" value="2">企业微信</el-option>
        </el-select>
      </el-form-item>
      <el-form-item>
        <el-button icon="el-icon-search" size="mini" type="primary" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button
          v-hasPermi="['cabinet:conf:add']"
          icon="el-icon-plus"
          plain
          size="mini"
          type="primary"
          @click="handleAdd"
        >新增
        </el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="confList" @selection-change="handleSelectionChange">
      <el-table-column align="center" label="微信菜单" prop="title"/>
      <el-table-column align="center" label="微信标题" prop="wxTitle"/>
      <el-table-column align="center" label="微信凭证" prop="appid"/>
      <el-table-column align="center" label="微信凭证密钥" prop="secret"/>
      <el-table-column align="center" label="类型" prop="type">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.cabinet_wx_type" :value="scope.row.type"/>
        </template>
      </el-table-column>
      <el-table-column align="center" class-name="small-padding fixed-width" label="操作">
        <template slot-scope="scope">
          <el-button
            icon="el-icon-edit"
            size="mini"
            type="text"
            @click="handleDetail(scope.row)"
          >token 值
          </el-button>
          <el-button
            v-hasPermi="['cabinet:conf:edit']"
            icon="el-icon-edit"
            size="mini"
            type="text"
            @click="handleUpdate(scope.row)"
          >修改
          </el-button>
          <el-button
            v-hasPermi="['cabinet:conf:remove']"
            icon="el-icon-delete"
            size="mini"
            type="text"
            @click="handleDelete(scope.row)"
          >删除
          </el-button>
        </template>
      </el-table-column>
    </el-table>

    <pagination
      v-show="total>0"
      :limit.sync="queryParams.pageSize"
      :page.sync="queryParams.pageNum"
      :total="total"
      @pagination="getList"
    />

    <!-- 添加或修改微信配置对话框 -->
    <el-dialog :title="title" :visible.sync="open" append-to-body width="500px">
      <el-form ref="form" :model="form" :rules="rules" label-width="100px">
        <el-form-item label="微信菜单" prop="wxMenuId">
          <!--
            小记：
              select 标签 :value 回显值，如果接收到的值是 Integer 类型的需要转换一下，
              转成 String 类型，如 :value="item.id+''" 就 OK 了
          -->
          <el-select v-model="form.wxMenuId" placeholder="请选择">
            <el-option
              v-for="item in wxMenuList"
              :key="item.id"
              :label="item.title"
              :value="item.id+''"
            >{{ item.title }}
            </el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="微信标题" prop="wxTitle">
          <el-input v-model="form.wxTitle" placeholder="请输入微信标题"/>
        </el-form-item>
        <el-form-item label="微信凭证ID" prop="appid">
          <el-input v-model="form.appid" placeholder="请输入appid/corpid"/>
        </el-form-item>
        <el-form-item label="微信凭证密钥" prop="secret">
          <el-input v-model="form.secret" placeholder="请输入secret/corpsecret"/>
        </el-form-item>
        <el-form-item label="类型" prop="type">
          <el-select v-model="form.type" placeholder="请选择类型">
            <!--
              小记：
                使用字典回显 select 的问题，传值需要转换一下保持数据类型一致
                字典默认使用的都是 String 类型，如果我们传的是 Integer 类型的
                就需要使用 Number 函授转换一下，如 :value="Number(dict.value)"
            -->
            <el-option
              v-for="dict in dict.type.cabinet_wx_type"
              :key="dict.value"
              :label="dict.label"
              :value="Number(dict.value)"
            ></el-option>
          </el-select>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>

    <!-- 查看 AccessToken 值 -->
    <el-dialog :visible.sync="openDetail" :z-index="1000" append-to-body title="查看 Token 值" width="500px">
      <el-form ref="formDetail" :model="formDetail" :rules="rules" label-width="110">
        <el-form-item label="access_token" prop="accessToken">
          <span style="flex: auto;float: right;color: #36a3f7;cursor:pointer;" @click="copy()">复制内容</span>
          <el-input ref="textarea" v-model="formDetail.accessToken" :disabled="true" type="textarea"/>
          <!-- autosize:高度自适应属性 -->
        </el-form-item>
        <el-form-item label="凭证有效时间" prop="createTime">
          <el-input v-model="formDetail.createTime" :disabled="true"/>
        </el-form-item>
      </el-form>
    </el-dialog>

  </div>
</template>

<script>
import {addConf, delConf, getConf, listConf, listMenuList, updateConf} from "@/api/cabinet/conf";
import {getAccessToken} from '@/api/cabinet/wechat'
import {parseTime} from '../../../../utils/cabinet'

export default {
  name: "Conf",
  dicts: ['cabinet_wx_type'],
  data() {
    return {
      // 遮罩层
      loading: true,
      // 选中数组
      ids: [],
      // 非单个禁用
      single: true,
      // 非多个禁用
      multiple: true,
      // 显示搜索条件
      showSearch: true,
      // 字典：类型
      cabinet_wx_type: null,
      // 总条数
      total: 0,
      // 微信配置表格数据
      confList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      openDetail: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        wxMenuId: null,
        wxTitle: null,
        appid: null,
        secret: null,
        accessToken: null,
        expiresIn: null,
        createTime: null,
        type: null
      },
      // 表单参数
      form: {},
      formDetail: {},
      // 表单校验
      rules: {},
      textarea: "",
      // 微信菜单下拉框
      wxMenuList: [],
    };
  },
  created() {
    this.getList();
    this.getMenuList();
  },
  methods: {
    /** 查询微信配置列表 */
    getList() {
      this.loading = true;
      listConf(this.queryParams).then(response => {
        this.confList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
    },
    // 微信菜单下拉框
    getMenuList() {
      listMenuList().then(response => {
        this.wxMenuList = response.data;
      })
    },
    // 取消按钮
    cancel() {
      this.open = false;
      this.reset();
    },
    // 表单重置
    reset() {
      this.form = {
        id: null,
        wxMenuId: null,
        wxTitle: null,
        appid: null,
        secret: null,
        accessToken: null,
        expiresIn: null,
        createTime: null,
        type: null
      };
      this.resetForm("form");
    },
    /** 搜索按钮操作 */
    handleQuery() {
      this.queryParams.pageNum = 1;
      this.getList();
    },
    /** 重置按钮操作 */
    resetQuery() {
      this.resetForm("queryForm");
      this.handleQuery();
    },
    // 多选框选中数据
    handleSelectionChange(selection) {
      this.ids = selection.map(item => item.id)
      this.single = selection.length !== 1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加微信配置";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const id = row.id || this.ids
      getConf(id).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改微信配置";
      });
    },
    /** 点击列表查看 */
    handleDetail(row) {
      this.reset();
      const id = row.id || this.ids
      getConf(id).then(response => {
        this.formDetail = response.data;
        this.openDetail = true;
        /*
          转换时间函数 parseTime 日期格式化 时间 -》字符串
        */
        if (response.data.createTime >= parseTime(new Date())) {
          this.$confirm('凭证已过期是否需要重新获取？', '提示', {
            confirmButtonText: '确定',
            cancelButtonText: '取消',
            type: 'warning'
          }).then(() => {
            // 执行业务逻辑
            getAccessToken({id: id}).then(response => {
              // 请求成功
              if (response.code == 200) {
                this.reset()
                this.formDetail.accessToken = response.data.token
                this.formDetail.expiresIn = response.data.expiresIn
                // 消息提示
                this.$message({
                  type: 'success',
                  message: '已更新!'
                });
              }
            });
          }).catch(() => {
            // 消息提示内容
          });
        } else {
          console.log("尚未过期！")
        }
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.id != null) {
            updateConf(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addConf(this.form).then(response => {
              this.$modal.msgSuccess("新增成功");
              this.open = false;
              this.getList();
            });
          }
        }
      });
    },
    /** 删除按钮操作 */
    handleDelete(row) {
      const ids = row.id || this.ids;
      this.$modal.confirm('是否确认删除微信配置编号为"' + ids + '"的数据项？').then(function () {
        return delConf(ids);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {
      });
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('cabinet/conf/export', {
        ...this.queryParams
      }, `conf_${new Date().getTime()}.xlsx`)
    },
    /* 点击获取 textarea 里的内容 */
    copy() {
      let text = this.$refs.textarea.value
      let oInput = document.createElement('input');
      oInput.value = text;
      document.body.appendChild(oInput);
      oInput.select(); // 选择对象;
      document.execCommand("Copy"); // 执行浏览器复制命令
      this.$message({
        message: '已成功复制到剪切板',
        type: 'success'
      });
      oInput.remove()
    },

  }
};
</script>
<style scoped>
/deep/ .el-textarea__inner {
  height: 150px;
}
</style>
