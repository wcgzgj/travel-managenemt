<template>
    <a-breadcrumb style="margin: 16px 0"></a-breadcrumb>
    <a-layout-content
            :style="{ background: '#fff', padding: '24px', margin: 0, minHeight: '280px' }"
    >


        姓名:
        <a-input v-model:value="user.name" placeholder="标题" disabled="true" />

        <br/>
        <br/>
        生日:
        <a-input v-model:value="user.birthdayStr" placeholder="日期"  />

        <br/>
        <br/>
        金额:
        <a-input v-model:value="user.name" placeholder="金额"  />
        <br/>
        <br/>

        <a-button type="primary" @click="save">保存</a-button>
        &nbsp;
        &nbsp;
        <a-button >
            <router-link to="/user-list">
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

    export default {
        name: "UserUpdate",

        setup() {
            /**
             * 页面加载的参数
             */
            const loading = ref(false);

            /**
             * 获取 url 中信息的工具
             */
            const route = useRoute();

            /**
             * uri 中传来的用户id
             */
            const userId = route.query.userId;

            /**
             * 向前端展示的用户信息
             */
            const user = ref({});


            const selectById = () => {
                axios.get("/user/selectById/"+userId).then(resp=> {
                    const data = resp.data;
                    if (data.success) {
                        console.log("查询数据成功");
                        user.value=data.content;
                    } else {
                        message.error(data.message);
                    }
                })
            }


            /**
             * 用户信息保存接口
             */
            const save = () => {
                axios.post("/uri",
                    // bill.value
                ).then(resp=> {
                    const data = resp.data;
                    if (data.success) {
                        message.success("修改成功");
                        // 修改成功，返回首页
                        router.push("/");
                    } else {
                        message.error(data.message);
                    }
                })
            }


            /**
             * axios get 的基本格式
             * 传输参数，需要使用 params 形式
             */
            axios.get("/uri",{
                params:{
                    // pageNum:bill.value.pageNum,
                    // pageSize:bill.value.pageSize,
                    // startDate:bill.value.startDate,
                    // endDate:bill.value.endDate,
                    // title:bill.value.title,
                    // typeId:bill.value.typeId
                }
            }).then(resp=> {
                const data = resp.data;
                if (data.success) {
                    console.log("查询数据成功");
                    console.log("查询出的数据条数为:"+data.content.list.length);
                    // bills.value=data.content.list; 为表格/列表中的数据赋值
                    console.log("传来的total值为:"+data.content.total);
                    // 修改分页信息中的 total 信息
                    pagination.value.total=data.content.total;
                } else {
                    message.error(data.message);
                }
            })


            /**
             * axios post 基本格式
             * 传输参数，必须使用封装好了的数据类型（如下面的 bill.value）
             * 后端接口，要加上 @RequestBody 注解（将 json 形式的数据自动转换）
             */
            axios.post("/uri",
                // bill.value
            ).then(resp=> {
                const data = resp.data;
                if (data.success) {
                    message.success("修改成功");
                    // 修改成功，返回首页
                    router.push("/");
                } else {
                    message.error(data.message);
                }
            })


            /**
             * onMounted 基本格式
             */
            onMounted(()=>{
                selectById();
            });

            return {
                loading,
                selectById,
                save,
                user
            };
        },
    }
</script>

<style scoped>

</style>