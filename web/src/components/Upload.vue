<template>
    <div>
        <a-button type="primary" v-on:click="selectFile()" >
            <template #icon><VerticalAlignTopOutlined /></template>
            上传文件
        </a-button>
        <input hidden type="file" v-on:change="upload()" id="file-upload-input"/>
        <a-image
                :width="200"
                :src="filePath"
        />
    </div>
</template>

<script>
    import { message } from 'ant-design-vue';
    import { VerticalAlignTopOutlined } from '@ant-design/icons-vue';
    import {ref} from "vue";
    import axios from "axios";
    import {onMounted} from "@vue/runtime-core";
    export default {
        name: "Upload",
        components: {
            VerticalAlignTopOutlined,
        },
        setup() {

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
                        filePath.value=data.content;
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



            /**
             * onMounted 基本格式
             */
            onMounted(()=>{

            });

            return {
                upload,
                filePath,
                selectFile
            };
        },
    }
</script>

<style scoped>

</style>