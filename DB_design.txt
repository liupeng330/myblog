blog table: 
id, summary, content, update time, create time, status (published, deleted, draft), show count, user id, categary id, 

user table:
id, name, password, status(active, inactive), last_login_time

comment user table:
id, name, email, blog url, remote ip

comment table:
id, content, commnet user id, create time, blog id

categary table:
id, name, create time, update time

tag table:
id, name, create time, update time

blog tag table:
blog id, tag id