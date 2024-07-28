<template>
    <div>
      <h2>新增地址</h2>
      <div>送餐城市：
        <select>
          <option>北京市</option>
          <option>天津市</option>
        </select>
      </div>
      <div>送餐地址：<input type="text" placeholder="地址或小区/写字楼/学校等"></div>
      <div>详细地址：<input type="text" placeholder="楼号/门牌号，例：1号101室"></div>
      <div>收餐人：<input type="text" placeholder="请输入收餐人姓名"></div>
      <div>联系电话：<input type="text" placeholder="请输入联系电话"></div>
      <div>送餐门店：
        <select>
          <option>门店1</option>
          <option>门店2</option>
        </select>
      </div>
      <button @click="$emit('change-view', 'AddressList')">取消</button>
      <button @click="save">保存</button>
    </div>
  </template>
  
  <script>
  export default {
    name: 'PAddAddress',
    methods: {
      save() {
        // 保存地址逻辑
        this.$emit('change-view', 'AddressList');
      }
    }
  };
  </script>
  
  <style>
  /* 添加你的样式 */
  </style>