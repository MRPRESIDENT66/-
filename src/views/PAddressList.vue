<template>
    <div>
      <h2>我的地址</h2>
      <div>收餐人 送餐地址 联系电话 操作</div>
      <button @click="$emit('change-view', 'PAddAddress')">添加新地址</button>
    </div>
  </template>
  
  <script>
  export default {
    name: 'PAddressList'
  };
  </script>
  
  <style>
  /* 添加你的样式 */
  </style>