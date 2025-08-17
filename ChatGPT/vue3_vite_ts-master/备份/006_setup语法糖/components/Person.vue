<template>
  <div class="person">
    <h2>姓名：{{ name }}</h2>
    <h2>年龄：{{ age }}</h2>
    <h2>地址：{{ address }}</h2>
    <button @click="changeName">修改名字</button>
    <button @click="changeAge">修改年龄</button>
    <button @click="showTel">查看联系方式</button>
  </div>
</template>

<!-- 存在两个script也是可以的，一个setup，一个配置name -->
<!-- <script lang="ts">
export default {
  name: 'Person'
}
</script> -->

<!-- 如果想在setup里面同时命名组件名称，需要下载插件 -->
<script lang="ts" setup name="Person">
let name = '张三'
let age = 18
let tel = '13888888888'
let address = '北京昌平区宏福苑·宏福科技园'

// 方法
function changeName() {
  name = 'zhang-san'
  console.log(name)
}
function changeAge() {
  age += 1
  console.log(age)
}
function showTel() {
  alert(tel)
}
</script>



<style scoped>
.person {
  background-color: skyblue;
  box-shadow: 0 0 10px;
  border-radius: 10px;
  padding: 20px;
}

button {
  margin: 0 5px;
}
</style>