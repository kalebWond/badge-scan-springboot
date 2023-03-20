SET IDENTITY_INSERT role_table ON
INSERT INTO  role_table(id,role) values (1,'Student')
INSERT INTO  role_table(id,role) values (2,'Faculty')
INSERT INTO  role_table(id,role) values (3,'Staff')
INSERT INTO  role_table(id,role) values (4,'Admin')
SET IDENTITY_INSERT role_table OFF

SET IDENTITY_INSERT member_table ON
    INSERT INTO member_table(id,email,firstName,lastName,role_id) values (1,'karna@miu.edu','Karna','Shrestha',1)
SET IDENTITY_INSERT member_table OFF

--
-- SET IDENTITY_INSERT membership_table ON
--     INSERT INTO member_table(id,endDate,membershipType,numberOfAllowances,role_id) values (1,'karna@miu.edu','Karna','Shrestha',1)
-- SET IDENTITY_INSERT member_table OFF



