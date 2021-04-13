export const mixin = {
  methods: {
    //提示信息
    notify(title, type) {
      this.$notify({
        title: title,
        type: type
      })
    },
    //根据相对地址获取绝对地址
    getUrl(url) {
      return `${this.$store.state.HOST}/${url}`
    },
    //获取歌手性别
    getSingerSex(value) {
      if (value == 0) {
        return '女';
      }
      if (value == 1) {
        return '男';
      }
      if (value == 2) {
        return '组合';
      }
      if (value == 3) {
        return '不明';
      }
      return value;
    },
    //获取歌手生日
    getSingerBirth(d) {
      return String(d).substr(0, 10);
    },
    //上传前的校验
    beforeAvatarUpload(file) {
      const isImg = (file.type === 'image/jpeg') || (file.type === 'image/png') || (file.type === 'image/jpg');
      const isLt2M = (file.size / 1024 / 1024) < 2;
      if (!isImg) {
        this.$message.error('上传头像图片只能是jpg或png或jpeg格式');
        return false;
      }
      if (!isLt2M) {
        this.$message.error('上传头像图片大小不能超过 2MB!');
        return false;
      }
      return true;
    },
    //上传图片成功之后
    handleAvatarSuccess(res, file) {
      let _this = this;
      if (res.code === 1) {
        // _this.imageUrl = URL.createObjectURL(file.raw);
        console.log(URL.createObjectURL(file.raw));
        _this.getData();
        _this.$notify({
          title: '上传成功',
          type: 'success'
        });
      } else {
        _this.$notify({
          title: '上传失败',
          type: 'error'
        });
      }
    },
    //弹出删除框
    handleDelete(id) {
      console.log(id);
      this.currentIndex = id;
      this.delVisible = true;
    },
    //多选 收集选中的index值作为数组传递给rowStyle变化样式
    handleSelectionChange(row) {
      this.multipleSelection = row;
      this.selectedNum = [];
      for (let item of row) {
        this.selectedNum.push(item.index);
      }
    },
    //实现样式变换 通过获取到的数组index值
    rowStyle({
      row,
      rowIndex
    }) {
      for (let i = 0; i < this.selectedNum.length; i++) {
        console.log(this.selectedNum[i]);
        if (rowIndex === this.selectedNum[i]) {
          return {
            backgroundColor: '#d2f5ff',
            color: '#1890ff'
          };
        }
      }
    },
    //动态添加index到row里面去
    tableRowClassName({
      row,
      rowIndex
    }) {
      row.index = rowIndex;
    },
    //批量删除已选择的项
    delSelected() {
      for (let item of this.multipleSelection) {
        console.log(item);
        this.handleDelete(item.id);
        this.deleteRow(item.id);
      }
      this.multipleSelection = [];
    },
    //上传歌曲前的校验
    beforeSongUpload(file) {
      //获取文件扩展名
      var testMsg = file.name.substring(file.name.lastIndexOf('.') + 1);
      // 判断是否为mp3或ogg格式
      const extension = (testMsg === 'mp3' | testMsg === 'ogg');
      if (!extension) {
        this.$message({
          message: "上传文件只能是mp3格式或ogg格式",
          type: "error"
        });
        return false;
      }
      return true;
    },
    //上传歌曲成功之后
    handleSongSuccess(res, file) {
      let _this = this;
      if (res.code === 1) {
        // _this.imageUrl = URL.createObjectURL(file.raw);
        console.log(URL.createObjectURL(file.raw));
        _this.getData();
        _this.$notify({
          title: '上传成功',
          type: 'success'
        });
      } else {
        _this.$notify({
          title: '上传失败',
          type: 'error'
        });
      }
    }
  }
}
