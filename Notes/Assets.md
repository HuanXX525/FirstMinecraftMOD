# 资源说明

## 文件夹

- `lang` 存放注册项目的名称到游戏显示名称的映射。书写规则`固定前缀.modid.id`，前缀实例如下：
  - block.xxx：方块名称
  - item.xxx：物品名称 
  - entity.xxx：实体名称 
  - tileentity.xxx：方块实体名称 
  - effect.xxx：状态效果名称
- `models` 存放模型，可以继承自游戏的类型，贴图需自定义
- `textures` 存放贴图
- `blockstates` 存放方块状态（有些方块形态会变化）