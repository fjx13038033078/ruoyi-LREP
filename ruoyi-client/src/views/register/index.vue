<template>
  <div class="register-container">
    <div class="register-box">
      <div class="register-left">
        <div class="register-image"></div>
      </div>
      <div class="register-form">
        <div class="register-title">
          <h2>用户注册</h2>
          <p>User Registration</p>
        </div>
        <el-form ref="registerForm" :model="registerForm" :rules="registerRules" class="register-form-content">
          <el-form-item prop="username">
            <el-input
              v-model="registerForm.username"
              type="text"
              placeholder="请输入账号"
              prefix-icon="el-icon-user"
            >
            </el-input>
          </el-form-item>
          <el-form-item prop="password">
            <el-input
              v-model="registerForm.password"
              type="password"
              placeholder="请输入密码"
              prefix-icon="el-icon-lock"
            >
            </el-input>
          </el-form-item>
          <el-form-item prop="confirmPassword">
            <el-input
              v-model="registerForm.confirmPassword"
              type="password"
              placeholder="请再次输入密码"
              prefix-icon="el-icon-lock"
            >
            </el-input>
          </el-form-item>
          <el-form-item prop="code" v-if="captchaEnabled">
            <el-input
              v-model="registerForm.code"
              placeholder="请输入验证码"
              style="width: 63%"
              prefix-icon="el-icon-key"
            >
            </el-input>
            <div class="register-code">
              <img :src="codeUrl" @click="getCode" class="register-code-img" />
            </div>
          </el-form-item>
          <el-form-item style="margin-bottom:15px;">
            <el-button
              :loading="loading"
              type="primary"
              style="width:100%;"
              @click.native.prevent="handleRegister"
            >
              <span v-if="!loading">注 册</span>
              <span v-else>注 册 中...</span>
            </el-button>
          </el-form-item>
          <div class="register-options">
            <router-link to="/login" class="login-link">返回登录</router-link>
          </div>
        </el-form>
        <div class="register-footer">
          © 2023 学习资源分享平台 All Rights Reserved
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import { getCodeImg, register } from "@/api/login";

export default {
  name: "Register",
  data() {
    const validatePassword = (rule, value, callback) => {
      if (value.length < 6) {
        callback(new Error("密码不能少于6个字符"));
      } else {
        callback();
      }
    };
    const validateConfirmPassword = (rule, value, callback) => {
      if (value === "") {
        callback(new Error("请再次输入密码"));
      } else if (value !== this.registerForm.password) {
        callback(new Error("两次输入密码不一致"));
      } else {
        callback();
      }
    };
    return {
      codeUrl: "",
      registerForm: {
        username: "",
        password: "",
        confirmPassword: "",
        code: "",
        uuid: ""
      },
      registerRules: {
        username: [
          { required: true, trigger: "blur", message: "请输入您的账号" },
          { min: 2, max: 20, message: "用户账号长度必须介于 2 和 20 之间", trigger: "blur" }
        ],
        password: [
          { required: true, trigger: "blur", message: "请输入您的密码" },
          { validator: validatePassword, trigger: "blur" }
        ],
        confirmPassword: [
          { required: true, trigger: "blur", message: "请再次输入密码" },
          { validator: validateConfirmPassword, trigger: "blur" }
        ],
        code: [{ required: true, trigger: "blur", message: "请输入验证码" }]
      },
      loading: false,
      captchaEnabled: true
    };
  },
  created() {
    this.getCode();
  },
  methods: {
    getCode() {
      getCodeImg().then(res => {
        this.captchaEnabled = res.captchaEnabled === undefined ? true : res.captchaEnabled;
        if (this.captchaEnabled) {
          this.codeUrl = "data:image/gif;base64," + res.img;
          this.registerForm.uuid = res.uuid;
        }
      });
    },
    handleRegister() {
      this.$refs.registerForm.validate(valid => {
        if (valid) {
          this.loading = true;
          register({
            username: this.registerForm.username,
            password: this.registerForm.password,
            code: this.registerForm.code,
            uuid: this.registerForm.uuid
          }).then(() => {
            this.loading = false;
            this.$alert("<div style='text-align: center'>注册成功</div>", "系统提示", {
              dangerouslyUseHTMLString: true,
              type: "success",
              showClose: false,
              center: true,
              confirmButtonText: "去登录",
              callback: action => {
                this.$router.push("/login");
              }
            });
          }).catch(() => {
            this.loading = false;
            this.getCode();
          });
        }
      });
    }
  }
};
</script>

<style lang="scss" scoped>
.register-container {
  display: flex;
  justify-content: center;
  align-items: center;
  height: 100%;
  background: #f5f5f5;
  background-image: linear-gradient(to bottom right, #6a11cb, #2575fc);
}

.register-box {
  display: flex;
  width: 960px;
  height: 600px;
  border-radius: 10px;
  box-shadow: 0 20px 50px rgba(0, 0, 0, 0.2);
  overflow: hidden;
  background: #fff;
}

.register-left {
  flex: 1;
  background: #eef6ff;
  position: relative;
}

.register-image {
  position: absolute;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  background-image: linear-gradient(to right bottom, #6a11cb, #2575fc);
  background-size: cover;
  background-position: center;
}

.register-form {
  flex: 1;
  padding: 50px;
  display: flex;
  flex-direction: column;
}

.register-title {
  text-align: center;
  margin-bottom: 40px;
  
  h2 {
    font-size: 26px;
    font-weight: 600;
    color: #333;
    margin-bottom: 10px;
  }
  
  p {
    font-size: 16px;
    color: #999;
    margin: 0;
  }
}

.register-form-content {
  flex: 1;
}

.register-footer {
  text-align: center;
  font-size: 12px;
  color: #999;
  margin-top: 20px;
}

.register-code {
  float: right;
  width: 33%;
  height: 40px;
  
  .register-code-img {
    width: 100%;
    height: 100%;
    cursor: pointer;
    border-radius: 4px;
  }
}

.register-options {
  display: flex;
  justify-content: space-between;
  margin-bottom: 20px;
  font-size: 14px;
  
  .login-link {
    color: #409EFF;
  }
}

::v-deep .el-input__inner {
  height: 44px;
  border-radius: 4px;
}

::v-deep .el-form-item {
  margin-bottom: 25px;
}
</style> 