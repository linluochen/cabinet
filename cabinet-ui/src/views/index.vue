<template>
  <div>
    <div style="padding: 20px;">
      <div class="top">
        <h1>平凡的人类</h1>
        <hr>
      </div>
      <div class="centre">
        <!--
        说明：
          :header-cell-style="{ textAlign: 'center' }" 表头居中
          :cell-style="{ textAlign: 'center' }" 表格居中
          <el-table-column> 中不添加 style='width：100' 属性就是自适应，加上就是固定宽度
        -->
        <el-table :cell-style="{ textAlign: 'center' }" :data="tableData" :header-cell-style="{ textAlign: 'center' }"
                  style="width: 100%">
          <el-table-column label="文章名称" prop="name"></el-table-column>
          <el-table-column label="地址" prop="url"></el-table-column>
          <el-table-column label="日期" prop="createTime"></el-table-column>
        </el-table>
      </div>
    </div>
  </div>

</template>
<script>
import '@/assets/styles/home.scss'
import {listCatalog} from '@/api/cabinet/catalog'

export default {
  name: 'Index',
  components: {},
  data() {
    return {
      // 默认表单数据
      tableData: []
    }
  },
  created() {
    this.getListAll();
  },
  methods: {
    getListAll() {
      listCatalog().then(response => {
        console.log(response)
        this.tableData = response.rows;
        this.loading = false;
      });
    }
  }
}
</script>
<style lang="scss" scoped>
</style>
