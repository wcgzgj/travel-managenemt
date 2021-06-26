<template>
    <a-breadcrumb style="margin: 16px 0"></a-breadcrumb>
    <a-layout-content
            :style="{ background: '#fff', padding: '24px', margin: 0, minHeight: '280px' }"
    >
        <h1 style="font-size: 30px">路线添加</h1>


        线路名称:
        <a-input v-model:value="route.rname" placeholder="线路名称"  />

        <br/>
        <br/>
        线路介绍:
        <a-input v-model:value="route.routeintroduce" placeholder="线路介绍"  />

        <br/>
        <br/>
        
        所属分类:
        <br/>
        <a-select
                v-model:value="route.cid"
                style="width: 140px"
                @focus="focus"
                ref="select"
        >
            <a-select-option v-for="category in categorys" :value="category.cid">{{category.cname}}</a-select-option>
        </a-select>

        <br/>
        <br/>


        旅游公司:
        <br/>
        <a-select
                v-model:value="route.sid"
                style="width: 140px"
                @focus="focus"
                ref="select"
        >
            <a-select-option v-for="seller in sellers" :value="seller.sid">{{seller.sname}}</a-select-option>
        </a-select>

        <br/>
        <br/>

        单价:
        <a-input v-model:value="route.price" placeholder="单价"  />

        <br/>
        <br/>


        缩略图:
        <!--这里要放上图片上传的组件-->
        <div>
            <a-button type="primary" v-on:click="selectFile()" >
                <template #icon><VerticalAlignTopOutlined /></template>
                上传文件
            </a-button>
            <input hidden type="file" v-on:change="upload()" id="file-upload-input"/>
            <br/>
            <br/>
            <a-image
                    :width="200"
                    :src="filePath"
            />
        </div>




        <br/>
        <br/>

        <a-button type="primary" @click="save">保存</a-button>
        &nbsp;
        &nbsp;
        <a-button >
            <router-link to="/route-list">
                返回
            </router-link>
        </a-button>


    </a-layout-content>
</template>

<script>
    import { defineComponent,ref } from 'vue';
    import {Tool} from "@/util/Tool";
    import { message } from 'ant-design-vue';
    import {computed} from "@vue/reactivity";
    import store from "@/store";
    import axios from 'axios';
    import {useRoute} from "vue-router";
    import router from '../../router';
    import {onMounted} from "@vue/runtime-core";
    import { VerticalAlignTopOutlined } from '@ant-design/icons-vue';
    export default {
        name: "RouteAdd",
        components: {
            VerticalAlignTopOutlined,
        },
        setup() {

            /**
             * 待添加的路线
             */
            const route = ref({
                rname:"",
                routeintroduce:"",
                cid:'1',
                sid:'1',
                price:0,
                rimage:"",
            });


            /**
             * 获取所有的分类
             */
            const categorys = ref([]);
            const getCategorys = () => {
                axios.get("/category/getAll").then(resp=> {
                    const data = resp.data;
                    if (data.success) {
                        console.log("查询数据成功");
                        console.log("查询出的数据条数为:"+data.content.length);
                        categorys.value=data.content;
                    } else {
                        message.error(data.message);
                    }
                })
            }

            /**
             * 获取所有的旅行社
             */
            const sellers = ref([]);
            const getSellers = () => {
                axios.get("/seller/getAll").then(resp=> {
                    const data = resp.data;
                    if (data.success) {
                        console.log("查询数据成功");
                        console.log("查询出的数据条数为:"+data.content.length);
                        sellers.value=data.content;
                    } else {
                        message.error(data.message);
                    }
                })
            }


            /**
             * 保存修改信息
             */
            const save = () => {
                console.log("save中的图片信息为:"+route.value.rimage);
                axios.post("/route/save",route.value).then(resp=> {
                    const data = resp.data;
                    if (data.success) {
                        message.success("添加成功");
                        // 修改成功，返回首页
                        router.push("/route-list");
                    } else {
                        message.error(data.message);
                    }
                })
            }


            onMounted(()=>{
                getSellers();
                getCategorys();
            });







            const filePath=ref("");

            /**
             * 上传函数
             */
            const upload = () => {
                /**
                 * 要上传的文件，是通过 FormData 传输的
                 */
                const formData = new FormData();
                /**
                 * 获取上传组件中，待上传的文件
                 */
                const file = document.querySelector('#file-upload-input').files[0];


                // 判断文件格式
                let suffixs = ["jpg", "jpeg", "png"];
                let fileName = file.name;
                let suffix = fileName.substring(fileName.lastIndexOf(".") + 1, fileName.length).toLowerCase();
                let validateSuffix = false;
                for (let i = 0; i < suffixs.length; i++) {
                    if (suffixs[i].toLowerCase() === suffix) {
                        validateSuffix = true;
                        break;
                    }
                }
                if (!validateSuffix) {
                    message.error(("文件格式不正确！只支持上传：" + suffixs.join(",")));
                    return;
                }


                formData.append('file',file);
                axios.post(process.env.VUE_APP_SERVER+'/file/upload',formData).then(resp=> {
                    const data = resp.data;
                    if (data.success) {
                        message.success("文件上传成功！");
                        filePath.value=process.env.VUE_APP_SERVER+'/travel-img/'+data.content;
                        /**
                         * 将上传后的新图片名称
                         * 放入待保存的参数中
                         */
                        route.value.rimage=data.content;
                        console.log("上传后的图片名称为:"+route.value.rimage);
                    } else {
                        message.error(data.message);
                    }
                })

            }


            /**
             * 点击上传按钮后，触发隐藏的上传框
             */
            const selectFile = () => {
                document.querySelector("#file-upload-input").click();
            }


            return {
                route,
                save,
                categorys,
                sellers,
                getSellers,
                getCategorys,
                upload,
                filePath,
                selectFile
            };
        },
    }
</script>

<style scoped>

</style>