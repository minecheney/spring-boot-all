BEGIN;
INSERT INTO `sec_permission`
VALUES (1, '测试页面', '/test', 1, 'page:test', NULL, 1, 0);
INSERT INTO `sec_permission`
VALUES (2, '测试页面-查询', '/**/test', 2, 'btn:test:query', 'GET', 1, 1);
INSERT INTO `sec_permission`
VALUES (3, '测试页面-添加', '/**/test', 2, 'btn:test:insert', 'POST', 2, 1);
INSERT INTO `sec_permission`
VALUES (4, '监控在线用户页面', '/monitor', 1, 'page:monitor:online', NULL, 2, 0);
INSERT INTO `sec_permission`
VALUES (5, '在线用户页面-查询', '/**/api/monitor/online/user', 2, 'btn:monitor:online:query', 'GET', 1,
        4);
INSERT INTO `sec_permission`
VALUES (6, '在线用户页面-踢出', '/**/api/monitor/online/user/kickout', 2, 'btn:monitor:online:kickout',
        'DELETE', 2, 4);
COMMIT;

BEGIN;
INSERT INTO `sec_role`
VALUES (1, '管理员', '超级管理员', 1544611947239, 1544611947239);
INSERT INTO `sec_role`
VALUES (2, '普通用户', '普通用户', 1544611947246, 1544611947246);
COMMIT;

BEGIN;
INSERT INTO `sec_role_permission`
VALUES (1, 1, 1);
INSERT INTO `sec_role_permission`
VALUES (2, 1, 2);
INSERT INTO `sec_role_permission`
VALUES (3, 1, 3);
INSERT INTO `sec_role_permission`
VALUES (4, 1, 4);
INSERT INTO `sec_role_permission`
VALUES (5, 1, 5);
INSERT INTO `sec_role_permission`
VALUES (6, 1, 6);
INSERT INTO `sec_role_permission`
VALUES (7, 2, 1);
INSERT INTO `sec_role_permission`
VALUES (8, 2, 2);
COMMIT;

BEGIN;
INSERT INTO `sec_user`
VALUES (1, 'admin', '$2a$10$64iuSLkKNhpTN19jGHs7xePvFsub7ZCcCmBqEYw8fbACGTE3XetYq', '管理员',
        '17300000000', 'admin@xkcoding.com', 785433600000, 1, 1, 1544611947032, 1544611947032);
INSERT INTO `sec_user`
VALUES (2, 'user', '$2a$10$OUDl4thpcHfs7WZ1kMUOb.ZO5eD4QANW5E.cexBLiKDIzDNt87QbO', '普通用户',
        '17300001111', 'user@xkcoding.com', 785433600000, 1, 1, 1544611947234, 1544611947234);
COMMIT;


BEGIN;
INSERT INTO `sec_user_role`
VALUES (1, 1, 1);
INSERT INTO `sec_user_role`
VALUES (2, 2, 2);
COMMIT;