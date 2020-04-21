# Blackboard LTI工具 Java 接入Demo

Learning Tools Interoperability (LTI) 是一个由 IMS Global Learning Consortium创造的标准 .

它的主要目的是使用一个学习系统之间的标准方式，通过外部服务工具连接学习系统（例如学习管理系统 LMS) ,这个标准描述了连接系统作为LTI Tool Consumer和被连接工具作为LTI Tool Provider。

LTI Consumer:

这是使用工具的服务。通常这是一个 学习管理系统 (LMS) 或者 用户门户。LTI Consumer需要提供用户信息和环境给LTI Tool Provider。此外LTI Consumer还要为用户提供认证担保给LTI Tool Provider。

LTI Tool Provider: 

这是给LTI Consumer提供服务的服务。这可以是 on-premises software 也可以是托管在LTI Cousumer外部的服务。

Blackboard Learn 作为 LTI Consumer：

1.  OAuth认证

2.  掌握Bb的RestAPI


其他内容请参考下方链接，或给我留言

* [学习工具互操作性 (LTI)](https://help.blackboard.com/zh-hans/Learn/Administrator/SaaS/Integrations/Learning_Tools_Interoperability)
