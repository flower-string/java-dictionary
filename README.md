# 设计思路
默认拥有一个用户名为admin的用户
admin下有测试时创建的各种单词本

使用新的用户名时会自动创建一个对应名称的文件夹，并自动导入cet单词本，用户可以新建单词本，也可以在单词本中新增，删除和修改单词。

项目使用java swing编写，flatlaf进行美化，maven进行打包构建

# 使用方法
1. 将项目导入idea
2. 按idea的提示下载运行项目所需的依赖环境
3. 运行VocabularyBook类

# 项目分工
## 刘敬超
1. 项目设计
2. 编写LoginPage类、VocabularyBookpanel类和Main类
## 陈云云
1. Word类、WordDialog类和Bookhandler类
2. 后期测试
## 相欣雨
1. 寻找单词本素材
2. TxtConverter类和VocabularyGame类


# 更新日志

## 2.0 
1. 增加了用户名的校验，防止了空用户名导致的报错
2. 将绝对路径改为相对于项目的路径，运行时不再需要手动修改
3. 简化了构造函数中的代码，将其拆分到了display()方法中

## 1.0
1. 完成了基本功能

# 问题
项目构建后无法正常导入cet单词本，需要手动将cet.txt文件放入对应的文件夹