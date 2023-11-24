<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="标题" prop="title">
        <el-input
          v-model="queryParams.title"
          placeholder="请输入标题"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="昵称" prop="nickname">
        <el-input
          v-model="queryParams.nickname"
          placeholder="请输入昵称"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="手机号" prop="phone">
        <el-input
          v-model="queryParams.phone"
          placeholder="请输入手机号"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="品种" prop="bread">
        <el-input
          v-model="queryParams.bread"
          placeholder="请输入品种"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="宠物类型" prop="type">
        <el-select v-model="queryParams.type" placeholder="请选择宠物类型" clearable size="small">
          <el-option
            v-for="dict in dict.type.pet_type"
            :key="dict.value"
            :label="dict.label"
            :value="dict.value"
          />
        </el-select>
      </el-form-item>
      <el-form-item label="性别" prop="sex">
        <el-select v-model="queryParams.sex" placeholder="请选择性别" clearable size="small">
          <el-option
            v-for="dict in dict.type.pet_sex"
            :key="dict.value"
            :label="dict.label"
            :value="dict.value"
          />
        </el-select>
      </el-form-item>
<!--      <el-form-item label="年龄" prop="age">-->
<!--        <el-input-->
<!--          v-model="queryParams.age"-->
<!--          placeholder="请输入年龄"-->
<!--          clearable-->
<!--          size="small"-->
<!--          @keyup.enter.native="handleQuery"-->
<!--        />-->
<!--      </el-form-item>-->
      <el-form-item label="城市" prop="city">
        <el-input
          v-model="queryParams.city"
          placeholder="请输入城市"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="具体位置" prop="address">
        <el-input
          v-model="queryParams.address"
          placeholder="请输入具体位置"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="用户ID" prop="openId">
        <el-input
          v-model="queryParams.openId"
          placeholder="请输入用户ID"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="是否完成" prop="isFinish">
        <el-select v-model="queryParams.isFinish" placeholder="请选择是否完成" clearable size="small">
          <el-option
            v-for="dict in dict.type.is_finish"
            :key="dict.value"
            :label="dict.label"
            :value="dict.value"
          />
        </el-select>
      </el-form-item>
<!--      <el-form-item label="浏览量" prop="view">-->
<!--        <el-input-->
<!--          v-model="queryParams.view"-->
<!--          placeholder="请输入浏览量"-->
<!--          clearable-->
<!--          size="small"-->
<!--          @keyup.enter.native="handleQuery"-->
<!--        />-->
<!--      </el-form-item>-->
      <el-form-item label="是否删除" prop="isDeleted">
        <el-select v-model="queryParams.isDeleted" placeholder="请选择是否删除" clearable size="small">
          <el-option
            v-for="dict in dict.type.is_deleted"
            :key="dict.value"
            :label="dict.label"
            :value="dict.value"
          />
        </el-select>
      </el-form-item>
<!--      <el-form-item label="创建时间">-->
<!--        <el-date-picker-->
<!--          v-model="daterangeCreateTime"-->
<!--          size="small"-->
<!--          style="width: 240px"-->
<!--          value-format="yyyy-MM-dd"-->
<!--          type="daterange"-->
<!--          range-separator="-"-->
<!--          start-placeholder="开始日期"-->
<!--          end-placeholder="结束日期"-->
<!--        ></el-date-picker>-->
<!--      </el-form-item>-->
<!--      <el-form-item label="更新时间">-->
<!--        <el-date-picker-->
<!--          v-model="daterangeUpdateTime"-->
<!--          size="small"-->
<!--          style="width: 240px"-->
<!--          value-format="yyyy-MM-dd"-->
<!--          type="daterange"-->
<!--          range-separator="-"-->
<!--          start-placeholder="开始日期"-->
<!--          end-placeholder="结束日期"-->
<!--        ></el-date-picker>-->
<!--      </el-form-item>-->
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <el-row :gutter="10" class="mb8">
<!--      <el-col :span="1.5">-->
<!--        <el-button-->
<!--          type="primary"-->
<!--          plain-->
<!--          icon="el-icon-plus"-->
<!--          size="mini"-->
<!--          @click="handleAdd"-->
<!--          v-hasPermi="['adopt:find:add']"-->
<!--        >新增</el-button>-->
<!--      </el-col>-->
      <el-col :span="1.5">
        <el-button
          type="success"
          plain
          icon="el-icon-edit"
          size="mini"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['adopt:find:edit']"
        >修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          plain
          icon="el-icon-delete"
          size="mini"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['adopt:find:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['adopt:find:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="findList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
<!--      <el-table-column label="id" align="center" prop="id" />-->
      <el-table-column label="标题" align="center" prop="title" show-overflow-tooltip />
      <el-table-column label="昵称" align="center" prop="nickname" />
      <el-table-column label="手机号" align="center" prop="phone" min-width="120px />
      <el-table-column label="品种" align="center" prop="bread" />
      <el-table-column label="宠物类型" align="center" prop="type" width="80">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.pet_type" :value="scope.row.type"/>
        </template>
      </el-table-column>
      <el-table-column label="性别" align="center" prop="sex" width="60">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.pet_sex" :value="scope.row.sex"/>
        </template>
      </el-table-column>
      <el-table-column label="年龄" align="center" prop="age" width="80" />
      <el-table-column label="城市" align="center" prop="city" />
      <el-table-column label="具体位置" align="center" prop="address" show-overflow-tooltip />
      <el-table-column label="描述" align="center" prop="content" show-overflow-tooltip />
      <el-table-column label="是否完成" align="center" prop="isFinish" width="80">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.is_finish" :value="scope.row.isFinish"/>
        </template>
      </el-table-column>
<!--      <el-table-column label="是否删除" align="center" prop="isDeleted" />-->
      <el-table-column label="创建时间" align="center" prop="createTime">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.createTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="更新时间" align="center" prop="updateTime">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.updateTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['adopt:find:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['adopt:find:remove']"
          >删除</el-button>
        </template>
      </el-table-column>
    </el-table>

    <pagination
      v-show="total>0"
      :total="total"
      :page.sync="queryParams.pageNum"
      :limit.sync="queryParams.pageSize"
      @pagination="getList"
    />

    <!-- 添加或修改寻宠对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="标题" prop="title">
          <el-input v-model="form.title" placeholder="请输入标题" />
        </el-form-item>
        <el-form-item label="昵称" prop="nickname">
          <el-input v-model="form.nickname" placeholder="请输入昵称" />
        </el-form-item>
        <el-form-item label="手机号" prop="phone">
          <el-input v-model="form.phone" placeholder="请输入手机号" />
        </el-form-item>
        <el-form-item label="品种" prop="bread">
          <el-input v-model="form.bread" placeholder="请输入品种" />
        </el-form-item>
        <el-form-item label="宠物类型" prop="type">
          <el-select v-model="form.type" placeholder="请选择宠物类型">
            <el-option
              v-for="dict in dict.type.pet_type"
              :key="dict.value"
              :label="dict.label"
:value="dict.value"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="图片前缀" prop="picPrefix">
          <el-input v-model="form.picPrefix" placeholder="请输入图片前缀" />
        </el-form-item>
        <el-form-item label="图片" prop="pictures">
          <el-input v-model="form.pictures" type="textarea" placeholder="请输入内容" />
        </el-form-item>
        <el-form-item label="性别" prop="sex">
          <el-select v-model="form.sex" placeholder="请选择性别">
            <el-option
              v-for="dict in dict.type.pet_sex"
              :key="dict.value"
              :label="dict.label"
:value="dict.value"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="年龄" prop="age">
          <el-input v-model="form.age" placeholder="请输入年龄" />
        </el-form-item>
        <el-form-item label="城市" prop="city">
          <el-input v-model="form.city" placeholder="请输入城市" />
        </el-form-item>
        <el-form-item label="具体位置" prop="address">
          <el-input v-model="form.address" placeholder="请输入具体位置" />
        </el-form-item>
        <el-form-item label="描述">
          <editor v-model="form.content" :min-height="192"/>
        </el-form-item>
        <el-form-item label="openId" prop="openId">
          <el-input v-model="form.openId" placeholder="请输入openId" />
        </el-form-item>
        <el-form-item label="是否完成" prop="isFinish">
          <el-select v-model="form.isFinish" placeholder="请选择是否完成">
            <el-option
              v-for="dict in dict.type.is_finish"
              :key="dict.value"
              :label="dict.label"
:value="dict.value"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="浏览量" prop="view">
          <el-input v-model="form.view" placeholder="请输入浏览量" />
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import { listFind, getFind, delFind, addFind, updateFind } from "@/api/adopt/find";

export default {
  name: "Find",
  dicts: ['pet_sex', 'is_deleted', 'pet_type', 'is_finish'],
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
      // 总条数
      total: 0,
      // 寻宠表格数据
      findList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 浏览量时间范围
      daterangeCreateTime: [],
      // 浏览量时间范围
      daterangeUpdateTime: [],
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        title: null,
        nickname: null,
        phone: null,
        bread: null,
        type: null,
        picPrefix: null,
        pictures: null,
        sex: null,
        age: null,
        city: null,
        address: null,
        content: null,
        openId: null,
        isFinish: null,
        view: null,
        isDeleted: null,
        createTime: null,
        updateTime: null
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        title: [
          { required: true, message: "标题不能为空", trigger: "blur" }
        ],
        nickname: [
          { required: true, message: "昵称不能为空", trigger: "blur" }
        ],
        phone: [
          { required: true, message: "手机号不能为空", trigger: "blur" }
        ],
        type: [
          { required: true, message: "宠物类型不能为空", trigger: "change" }
        ],
        picPrefix: [
          { required: true, message: "图片前缀不能为空", trigger: "blur" }
        ],
        pictures: [
          { required: true, message: "图片不能为空", trigger: "blur" }
        ],
        city: [
          { required: true, message: "城市不能为空", trigger: "blur" }
        ],
        address: [
          { required: true, message: "具体位置不能为空", trigger: "blur" }
        ],
        content: [
          { required: true, message: "描述不能为空", trigger: "blur" }
        ],
        openId: [
          { required: true, message: "openId不能为空", trigger: "blur" }
        ],
        isFinish: [
          { required: true, message: "是否完成不能为空", trigger: "change" }
        ],
        view: [
          { required: true, message: "浏览量不能为空", trigger: "blur" }
        ],
        createTime: [
          { required: true, message: "创建时间不能为空", trigger: "blur" }
        ],
      }
    };
  },
  created() {
    this.getList();
  },
  methods: {
    /** 查询寻宠列表 */
    getList() {
      this.loading = true;
      this.queryParams.params = {};
      if (null != this.daterangeCreateTime && '' != this.daterangeCreateTime) {
        this.queryParams.params["beginCreateTime"] = this.daterangeCreateTime[0];
        this.queryParams.params["endCreateTime"] = this.daterangeCreateTime[1];
      }
      if (null != this.daterangeUpdateTime && '' != this.daterangeUpdateTime) {
        this.queryParams.params["beginUpdateTime"] = this.daterangeUpdateTime[0];
        this.queryParams.params["endUpdateTime"] = this.daterangeUpdateTime[1];
      }
      listFind(this.queryParams).then(response => {
        this.findList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
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
        title: null,
        nickname: null,
        phone: null,
        bread: null,
        type: null,
        picPrefix: null,
        pictures: null,
        sex: null,
        age: null,
        city: null,
        address: null,
        content: null,
        openId: null,
        isFinish: null,
        view: null,
        isDeleted: null,
        createTime: null,
        updateTime: null
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
      this.daterangeCreateTime = [];
      this.daterangeUpdateTime = [];
      this.resetForm("queryForm");
      this.handleQuery();
    },
    // 多选框选中数据
    handleSelectionChange(selection) {
      this.ids = selection.map(item => item.id)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加寻宠";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const id = row.id || this.ids
      getFind(id).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改寻宠";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.id != null) {
            updateFind(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addFind(this.form).then(response => {
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
      this.$modal.confirm('是否确认删除寻宠编号为"' + ids + '"的数据项？').then(function() {
        return delFind(ids);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('adopt/find/export', {
        ...this.queryParams
      }, `find_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
