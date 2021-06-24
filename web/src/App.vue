<template>


  <a-layout>

    <Header/>

    <a-layout>

      <Sider/>

      <a-layout style="padding: 0 24px 24px">

        <router-view/>

        <Footer/>

      </a-layout>
    </a-layout>
  </a-layout>
</template>


<script lang="ts">
  import { defineComponent} from 'vue';
  import Footer from './components/Footer.vue'
  import Header from "./components/Header.vue";
  import Sider from "./components/Sider.vue";

  export default defineComponent({
    name: 'Home',
    components: {
      Footer,
      Header,
      Sider
    },
  });
</script>

<style lang="scss">

</style>
