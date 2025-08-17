/// <reference types="vite/client" />

//避免.ts文件识别不了.vue文件
declare module "*.vue" {
    import Vue from "vue";
    export default Vue;
}
