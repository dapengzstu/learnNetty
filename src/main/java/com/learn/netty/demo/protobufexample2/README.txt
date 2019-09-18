这个就是模拟不要把解码器写死成某一个类

message自描述的解决方法

解决方法就是：
把很多个message写在一个message里面，成为一个属性，定义一个描述的enum，描述这个属性是哪种message
只要传入那个最大的message就好了


附加任务：
1.将生成的java文件放在git submodule
2.  git subtree 这个方法不错