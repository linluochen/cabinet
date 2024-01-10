<template>
  <div class="app-container">
    <!-- 配置 PDF tables 标签页面 -->
    <div>
      <el-tabs v-model="activeName" @tab-click="handleClick">
        <el-tab-pane label="列表查询" name="first">
          <!-- 页面实现 -->
          <div>
            <el-table
              :data="tableData"
              style="width: 100%">
              <el-table-column label="书名" prop="book" width="180"/>
              <el-table-column label="作者" prop="name" width="180"/>
              <el-table-column label="地址" prop="url"/>
              <el-table-column align="center" class-name="small-padding fixed-width" label="操作">
                <template slot-scope="scope">
                  <el-button
                    icon="el-icon-edit"
                    size="mini"
                    type="text"
                    @click="open"
                  >验证
                  </el-button>
                  <el-button
                    icon="el-icon-edit"
                    size="mini"
                    type="text"
                    @click="preview(scope.row)"
                  >预览
                  </el-button>
                </template>
              </el-table-column>
            </el-table>
          </div>
        </el-tab-pane>
        <!--        <el-tab-pane label="配置管理" name="second">
                  &lt;!&ndash; 页面实现 &ndash;&gt;
                </el-tab-pane>-->
      </el-tabs>
    </div>
    <!-- 预览窗口 -->
    <el-dialog :title="pdf.title" :visible.sync="pdfPreviewDialog" center>
      <pdf v-for="item in pdf.numPages" :key="item" :page="item" :src="pdf.src"/>
    </el-dialog>
  </div>
</template>

<script>
import {pdfOperator} from "@/api/cabinet/pdf";
import pdf from 'vue-pdf'

export default {
  name: 'index',
  components: {
    pdf
  },
  data() {
    return {
      // 首选 tables 页面
      activeName: 'first',
      // 表格内容
      tableData: [{
        book: '红楼梦',
        name: '曹雪芹',
        url: 'http://mozilla.github.io/pdf.js/web/compressed.tracemonkey-pldi-09.pdf'
      }, {
        book: '西游记',
        name: '吴承恩',
        url: 'http://mozilla.github.io/pdf.js/web/compressed.tracemonkey-pldi-09.pdf'
      }, {
        book: '水浒传',
        name: '施耐庵',
        url: 'http://mozilla.github.io/pdf.js/web/compressed.tracemonkey-pldi-09.pdf'
      }],
      // 默认弹出层不显示
      pdfPreviewDialog: false,
      // 配置 PDF 预览相关参数
      pdf: {
        // 标题
        title: "",
        // 文件要显示的路径
        src: "",
        // PDF 的总页数
        numPages: 0,
      }
    };
  },
  methods: {
    // 操作 - 验证调用的方法
    open() {
      this.$prompt('请输入要验证的文字', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
      }).then(({value}) => {
        const query = {
          flag: false,
          str: value
        }
        pdfOperator(query).then(response => {
          if (response) {
            this.$message({
              type: 'success',
              message: '验证成功！'
            });
          } else {
            this.$message({
              type: 'error',
              message: '验证失败内容不符合：' + value
            });
          }
        })
      }).catch(() => {
        this.$message({
          type: 'info',
          message: '取消验证'
        });
      });
    },
    // 切换 tables 方法
    handleClick(tab, event) {
      console.log(tab, event);
    },
    // 预览 PDF 方法
    preview(row) {
      // 获取当前要预览的文件路径
      this.pdf.src = pdf.createLoadingTask(row.url)
      this.pdf.src.promise.then((pdf) => {
        this.pdf.numPages = pdf.numPages
      })
      this.pdf.title = "预览文件" // 弹出层标题
      this.pdfPreviewDialog = true // 打开弹出层
    },
  }
}
</script>

<style scoped>

</style>
