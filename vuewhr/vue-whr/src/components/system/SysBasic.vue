<template>
  <div style="margin-top: 10px">
    <el-tabs v-model="defaultTab" type="card">
      <el-tab-pane label="部门管理" name="depMana">
        <dep-mana></dep-mana>
      </el-tab-pane>
      <el-tab-pane label="职位管理" name="positionMana">
        <pos-mana state="position"></pos-mana>
      </el-tab-pane>
      <el-tab-pane label="职称管理" name="jobTitleMana">
        <pos-mana state="jobtitle"></pos-mana>
      </el-tab-pane>
      <el-tab-pane label="奖惩规则" name="ecCfg">
        <ec-mana></ec-mana>
      </el-tab-pane>
      <el-tab-pane label="权限组" name="menuRole">
        <menu-role></menu-role>
      </el-tab-pane>
    </el-tabs>
  </div>
</template>
<script>
import DepMana from "@/components/system/basic/DepMana.vue";
import ECMana from "@/components/system/basic/ECMana.vue";
import JobTitleMana from "@/components/system/basic/JobTitleMana.vue";
import MenuRole from "@/components/system/basic/MenuRole.vue";
import PosMana from "@/components/system/basic/PosMana.vue";
export default {
  data() {
    return {
      defaultTab: "depMana"
    };
  },
  components: {
    "dep-mana": DepMana,
    "ec-mana": ECMana,
    "job-title-mana": JobTitleMana,
    "menu-role": MenuRole,
    "pos-mana": PosMana
  }
};
</script>