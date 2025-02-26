<template>
  <div>
    <div class="app-container">
      <!-- 新增课程按钮 -->
      <el-row :gutter="20" class="mb-20" style="margin-bottom: 20px;">
        <el-col>
          <el-button type="primary" @click="handleAddCourse" v-hasPermi="['resource:course:add']">新增课程</el-button>
        </el-col>
      </el-row>

      <!-- 课程列表 -->
      <el-table :data="courseList" v-loading="loading" style="width: 100%" border>
        <el-table-column label="课程ID" prop="courseId" align="center"></el-table-column>
        <el-table-column label="课程名称" prop="courseName" align="center"></el-table-column>
        <el-table-column label="课程描述" prop="description" align="center">
          <template #default="scope">
            <span>{{ scope.row.description.slice(0, 5) }}...</span>
          </template>
        </el-table-column>
        <el-table-column label="课程价格" prop="price" align="center"></el-table-column>
        <el-table-column label="授课教师" prop="instructor" align="center"></el-table-column>
        <el-table-column label="操作" align="center" width="350px">
          <template #default="scope">
            <el-button type="info" size="mini" @click="handleView(scope.row)">查看详情</el-button>
            <el-button type="warning" size="mini" @click="handleEdit(scope.row)" v-hasPermi="['resource:course:edit']">修改</el-button>
            <el-button type="danger" size="mini" @click="handleDelete(scope.row)" v-hasPermi="['resource:course:delete']">删除</el-button>
            <el-button type="success" size="mini" @click="handleBuy(scope.row)" v-hasPermi="['resource:course:purchase']">购买</el-button>
          </template>
        </el-table-column>
      </el-table>

      <!-- 分页组件 -->
      <pagination
        v-show="total > 0"
        :total="total"
        :page.sync="queryParams.pageNum"
        :limit.sync="queryParams.pageSize"
        @pagination="fetchCourseList"
      />

      <!-- 查看课程详情对话框 -->
      <el-dialog :visible.sync="viewDialogVisible" title="查看课程详情" width="40%">
        <el-form label-width="100px">
          <el-form-item label="课程名称">
            <el-input v-model="currentCourse.courseName" readonly></el-input>
          </el-form-item>
          <el-form-item label="课程描述">
            <el-input v-model="currentCourse.description" type="textarea" readonly></el-input>
          </el-form-item>
          <el-form-item label="课程价格">
            <el-input v-model="currentCourse.price" readonly></el-input>
          </el-form-item>
          <el-form-item label="授课教师">
            <el-input v-model="currentCourse.instructor" readonly></el-input>
          </el-form-item>
        </el-form>
        <div slot="footer" class="dialog-footer">
          <el-button @click="viewDialogVisible = false">关闭</el-button>
        </div>
      </el-dialog>

      <!-- 新增/修改课程对话框 -->
      <el-dialog :visible.sync="addDialogVisible" :title="dialogTitle" width="40%">
        <el-form ref="courseForm" :model="courseData" label-width="100px" :rules="rules">
          <el-form-item label="课程名称" prop="courseName">
            <el-input v-model="courseData.courseName"></el-input>
          </el-form-item>
          <el-form-item label="课程描述" prop="description">
            <el-input v-model="courseData.description" type="textarea"></el-input>
          </el-form-item>
          <el-form-item label="课程价格" prop="price">
            <el-input v-model="courseData.price" type="number" step="0.01"></el-input>
          </el-form-item>
          <el-form-item label="授课教师" prop="instructor">
            <el-input v-model="courseData.instructor"></el-input>
          </el-form-item>
        </el-form>
        <div slot="footer" class="dialog-footer">
          <el-button @click="addDialogVisible = false">取消</el-button>
          <el-button type="primary" @click="submitCourse">提交</el-button>
        </div>
      </el-dialog>
    </div>
  </div>
</template>

<script>
import { listAllCourses, addCourse, updateCourse, deleteCourse } from '@/api/resource/course';
import {addPurchase} from "@/api/resource/purchase";

export default {
  data() {
    return {
      loading: true,
      courseList: [],
      total: 0,
      queryParams: {
        pageNum: 1,
        pageSize: 10
      },
      addDialogVisible: false,
      dialogTitle: '',
      courseData: {},
      viewDialogVisible: false, // 控制查看对话框的显示
      currentCourse: {},       // 存储当前课程的详细信息
      rules: {
        courseName: [{ required: true, message: '请输入课程名称', trigger: 'blur' }],
        description: [{ required: true, message: '请输入课程描述', trigger: 'blur' }],
        price: [{ required: true, message: '请输入课程价格', trigger: 'blur' }],
        instructor: [{ required: true, message: '请输入授课教师', trigger: 'blur' }]
      }
    };
  },
  created() {
    this.fetchCourseList();
  },
  methods: {
    fetchCourseList() {
      this.loading = true;
      listAllCourses(this.queryParams).then(response => {
        this.courseList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
    },
    handleAddCourse() {
      this.dialogTitle = '新增课程';
      this.addDialogVisible = true;
      this.courseData = {};
    },
    handleView(row) {
      this.currentCourse = { ...row };
      this.viewDialogVisible = true;
    },
    handleEdit(row) {
      this.dialogTitle = '修改课程';
      this.addDialogVisible = true;
      this.courseData = { ...row };
    },
    submitCourse() {
      this.$refs.courseForm.validate(valid => {
        if (valid) {
          if (this.courseData.courseId) {
            updateCourse(this.courseData).then(() => {
              this.$message.success('修改成功');
              this.addDialogVisible = false;
              this.fetchCourseList();
            });
          } else {
            addCourse(this.courseData).then(() => {
              this.$message.success('新增成功');
              this.addDialogVisible = false;
              this.fetchCourseList();
            });
          }
        }
      });
    },
    handleDelete(row) {
      this.$confirm('确认删除该课程吗？', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        deleteCourse(row.courseId).then(() => {
          this.$message.success('删除成功');
          this.fetchCourseList();
        });
      });
    },
    // 新增购买记录
    handleBuy(row) {
      this.$confirm(`确认购买课程【${row.courseName}】？`, '确认购买', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'info'
      }).then(() => {
        const purchaseData = {
          courseId: row.courseId,
          price: row.price,
        };
        addPurchase(purchaseData).then(() => {
          this.$message.success('购买成功');
        })
      });
    },
  }
};
</script>
