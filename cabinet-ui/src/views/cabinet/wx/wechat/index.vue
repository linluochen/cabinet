<template>
  <div class="app-container">
    <div style="padding: 7px;background-color: #ffffff;border: solid 3px #f8f8f8;margin-left: 4px">
      <el-descriptions title="微信开发">
        <el-descriptions-item label="描述">集成个人使用过的微信相关 API 组件功能</el-descriptions-item>
      </el-descriptions>
    </div>

    <!-- 微信菜单 -->
    <div style="padding: 7px;margin-left: 4px">
      <el-collapse v-model="activeName" accordion>
        <el-collapse-item v-for="(it, inx) in menuList" :key="inx" :title="it.title" :name="inx">
          <div v-for="(item, index) in it.children" :key="index">
            <el-card class="box-card">
              <div slot="header" class="clearfix" style="text-align: center">
                <span>{{ item.title }}</span>
              </div>
              <div class="text item" style="height: 40px">
                <span class="text-wrapper">{{ item.describes }}</span>
              </div>
              <div style="text-align: center">
                <el-button type="button" @click="joint(item.title)">点击测试</el-button>
              </div>
            </el-card>
          </div>
        </el-collapse-item>
      </el-collapse>
    </div>



    <!-- 菜单弹窗 -->
    <el-dialog :visible.sync="dialogVisibleToken" title="选择配置" width="30%">
      <!-- 1. 获取 AccessToken 弹窗 -->
      <div v-if="jointVal == '获取 token 凭证'">
        <el-form ref="form" :model="form" label-width="120px">
          <el-form-item label="微信配置">
            <span style="flex: auto;float: right;color: #36a3f7;cursor:pointer;" @click="sendToken(value)">执行请求</span>
            <el-select v-model="value" placeholder="请选择" style="width: 80%">
              <el-option
                v-for="item in wxConfList"
                :key="item.id"
                :label="item.wxTitle"
                :value="item.id+''"
              ></el-option>
            </el-select>
          </el-form-item>
          <el-form-item label="Token 凭证" prop="accessToken">
            <span style="flex: auto;float: right;color: #36a3f7;cursor:pointer;" @click="copy()">复制内容</span>
            <el-input ref="textarea" v-model="accessToken" :disabled="true" style="width: 80%;height: 150px"
                      type="textarea"/>
          </el-form-item>
          <el-form-item label="有效时间(秒)" prop="expiresIn">
            <el-input v-model="expiresIn" :disabled="true" style="width: 80%" type="text"/>
          </el-form-item>
          <el-form-item class="offBut">
            <el-button @click="cancel">关 闭</el-button>
          </el-form-item>
        </el-form>
      </div>
      <div v-else-if="jointVal == '获取 IP 白名单'">
        <!-- 2. 获取 IP 弹窗 -->
        <el-form ref="form" :model="form" label-width="120px">
          <el-form-item label="微信配置">
            <span style="flex: auto;float: right;color: #36a3f7;cursor:pointer;" @click="sendIP(value)">执行请求</span>
            <el-select v-model="value" placeholder="请选择" style="width: 80%">
              <el-option
                v-for="item in wxConfList"
                :key="item.id"
                :label="item.title"
                :value="item.id+''"
              ></el-option>
            </el-select>
          </el-form-item>
          <el-form-item label="IP 地址" prop="accessToken">
            <span style="flex: auto;float: right;color: #36a3f7;cursor:pointer;" @click="copy()">复制内容</span>
            <el-input ref="textarea" v-model="value" :disabled="true" style="width: 80%;height: 150px"
                      type="textarea"/>
          </el-form-item>
          <el-form-item class="offBut">
            <el-button @click="cancel">关 闭</el-button>
          </el-form-item>
        </el-form>
      </div>
      <div v-else-if="jointVal == '获取 jsapi_ticket 签名'">
      </div>
      <div v-else-if="jointVal == '获取 code 信息'">
      </div>
      <div v-else-if="jointVal == '消息回复'">
      </div>
      <div v-else-if="jointVal == '模板消息'">
      </div>
      <div v-else-if="jointVal == '获取 openID'">
      </div>
      <div v-else-if="jointVal == '用户基本信息'">
      </div>
      <div v-else-if="jointVal == '获取用户列表'">
      </div>
      <div v-else-if="jointVal == '黑名单管理'">
      </div>
      <div v-else-if="jointVal == '生成临时二维码'">
      </div>
      <div v-else-if="jointVal == '生成永久二维码'">
      </div>
<!--      <div v-else-if="jointVal == ''">-->
<!--      </div>-->
<!--      <div v-else-if="jointVal == ''">-->
<!--      </div>-->
<!--      <div v-else-if="jointVal == ''">-->
<!--      </div>-->
<!--      <div v-else-if="jointVal == ''">-->
<!--      </div>-->
<!--      <div v-else-if="jointVal == ''">-->
<!--      </div>-->
<!--      <div v-else-if="jointVal == ''">-->
<!--      </div>-->
<!--      <div v-else-if="jointVal == ''">-->
<!--      </div>-->
<!--      <div v-else-if="jointVal == ''">-->
<!--      </div>-->
<!--      <div v-else-if="jointVal == ''">-->
<!--      </div>-->
<!--      <div v-else-if="jointVal == ''">-->
<!--      </div>-->
<!--      <div v-else-if="jointVal == ''">-->
<!--      </div>-->
<!--      <div v-else-if="jointVal == ''">-->
<!--      </div>-->
<!--      <div v-else-if="jointVal == ''">-->
<!--      </div>-->
<!--      <div v-else-if="jointVal == ''">-->
<!--      </div>-->
<!--      <div v-else-if="jointVal == ''">-->
<!--      </div>-->
    </el-dialog>


  </div>
</template>

<script>
import {getAccessToken, listConfList, listMenuList} from '@/api/cabinet/wechat'

export default {
  name: 'index',
  data() {
    return {
      activeName: '0',
      // 菜单列表
      menuList: [],
      dialogVisibleToken:false,
      // 查询所有已有 Wx 配置
      wxConfList: [],
      // 1.token
      value: "",
      // 控制展示内容
      jointVal: null,
      accessToken: "",
      expiresIn: "",
      form: {},
    }
  },
  created() {
    this.getList()
  },
  methods: {
    // 查询菜单列表
    getList() {
      listMenuList().then(response => {
        this.menuList = this.handleTree(response.data,"id") ;
      });
    },
    getListConfList() {
      listConfList().then(response => {
        this.wxConfList = response.data;
      });
    },
    // 测试按钮
    joint(val) {
      // 获取标题
      this.jointVal = val
      this.dialogVisibleToken = true
      // 调用下拉框赋值
      this.getListConfList()
    },
    // 取消按钮
    cancel() {
      this.dialogVisibleToken = false;
    },
    // 执行请求 token 方法
    sendToken(val) {
      getAccessToken({id: val}).then(response => {
        // 请求成功
        if (response.code == 200) {
          this.accessToken = response.data.token
          this.expiresIn = response.data.expiresIn
        }
      });
    },
    //  执行请求 ip 方法
    sendIP(){

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
}
</script>

<style scoped>
.offBut{
  display: flex;
  justify-content: center;
  margin-left: -120px
}

.text {
  font-size: 14px;
}

.item {
  margin-bottom: 18px;
}

.clearfix:before,
.clearfix:after {
  display: table;
  content: "";
}

.clearfix:after {
  clear: both
}

.box-card {
  width: 280px;
  float: left;
  margin: 5px;
}

/deep/ .el-card__body {
  height: auto;
  height: 120px;
}

.text-wrapper {
  word-break: break-all;
  word-wrap: break-word;
  /* 文字超出限制隐藏，显示 ...  */
  display: -webkit-box;
  overflow: hidden;
  text-overflow: ellipsis;
  /** 控制显示行数 */
  -webkit-line-clamp: 2;
  -webkit-box-orient: vertical;
}

/deep/ .el-textarea__inner {
  height: 150px;
}

</style>
