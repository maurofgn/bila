drop TABLE bilarow

CREATE TABLE bilarow
(
   nodeType varchar(20),
   lev int,
   code varchar(60),
   description varchar(60),
   amountYear double,
   amountYearPre double,
   amountDelta  double,
   summary boolean,
   total boolean   
)

truncate table bilarow

SELECT * FROM bilarow
order by nodeType, code

SELECT nodeType,lev,summary, count(*) FROM bilarow group by nodeType,lev,summary order by nodeType,lev,summary

SELECT * 
FROM bilarow 
order by case 
when nodeType = 'ATTIVO' then 1
when nodeType = 'PASSIVO' then 2
when nodeType = 'COSTI' then 3
when nodeType = 'RICAVI' then 4
end, 
lev,code, summary

SELECT decode(r.nodeType), r.* FROM bilarow r

SELECT  nodeType, 
case 
when nodeType = 'ATTIVO' then 1
when nodeType = 'PASSIVO' then 2
when nodeType = 'COSTI' then 3
when nodeType = 'RICAVI' then 4
end nt
 FROM bilarow  




--------------

INSERT INTO bilarow
(
   nodeType,
   lev,
   code,description,
   amountYear,amountYearPre,amountDelta,
   summary,total
)
SELECT  
coalesce(p1.node_type,p2.node_type,p3.node_type, null) node_type,
0 lev,
c.code, 
c.description, 
sum(case when year(d.date_reg) = 2015 then case when r.debit then 1 else -1 end * r.amount else 0 end) amountYear,
sum(case when year(d.date_reg) = 2014 then case when r.debit then 1 else -1 end * r.amount else 0 end ) amountYearPre,
sum(case when year(d.date_reg) = 2015 then case when r.debit then 1 else -1 end * r.amount else 0 end -
	case when year(d.date_reg) = 2014 then case when r.debit then 1 else -1 end * r.amount else 0 end) amountDelta,
c.summary, c.total
--r.* 
FROM document_row r
inner join document d on d.id = r.document_id
inner join account a on a.id = r.account_id
inner join account_cee c on c.id = a.account_cee_id
inner join account_cee p1 on p1.id = c.parent_id
left join account_cee p2 on p2.id = p1.parent_id
left join account_cee p3 on p3.id = p2.parent_id
where coalesce(p1.node_type,p2.node_type,p3.node_type, null) not in ('ECONOMICO','PATRIMONIALE')
group by
--coalesce(p3.node_type,p2.node_type,p1.node_type, null) ,
c.code, c.description, 
c.summary, c.total
--order by c.code



INSERT INTO bilarow
(
   nodeType,
   lev,
   code,description,
   amountYear,amountYearPre,amountDelta,
   summary,total
)
SELECT  
coalesce(p2.node_type,p3.node_type, null) node_type,
1 lev,
p1.code, 
p1.description, 
sum(case when year(d.date_reg) = 2015 then case when r.debit then 1 else -1 end * r.amount else 0 end) amountYear,
sum(case when year(d.date_reg) = 2014 then case when r.debit then 1 else -1 end * r.amount else 0 end ) amountYearPre,
sum(case when year(d.date_reg) = 2015 then case when r.debit then 1 else -1 end * r.amount else 0 end -
	case when year(d.date_reg) = 2014 then case when r.debit then 1 else -1 end * r.amount else 0 end) amountDelta,
p1.summary, p1.total 
FROM document_row r
inner join document d on d.id = r.document_id
inner join account a on a.id = r.account_id
inner join account_cee c on c.id = a.account_cee_id
inner join account_cee p1 on p1.id = c.parent_id
inner join account_cee p2 on p2.id = p1.parent_id
left join account_cee p3 on p3.id = p2.parent_id
where p1.summary = true
and coalesce(p2.node_type,p3.node_type, null) not in ('ECONOMICO','PATRIMONIALE')
group by
coalesce(p3.node_type,p2.node_type,p1.node_type, null),
p1.code, 
p1.description, 
p1.summary, p1.total 
order by p1.code



INSERT INTO bilarow
(
   nodeType,
   lev,
   code,description,
   amountYear,amountYearPre,amountDelta,
   summary,total
)
SELECT  
coalesce(p3.node_type, null) node_type,
2 lev,
p2.code, 
p2.description, 
sum(case when year(d.date_reg) = 2015 then case when r.debit then 1 else -1 end * r.amount else 0 end) amountYear,
sum(case when year(d.date_reg) = 2014 then case when r.debit then 1 else -1 end * r.amount else 0 end ) amountYearPre,
sum(case when year(d.date_reg) = 2015 then case when r.debit then 1 else -1 end * r.amount else 0 end -
	case when year(d.date_reg) = 2014 then case when r.debit then 1 else -1 end * r.amount else 0 end) amountDelta,
p2.summary, p2.total 
FROM document_row r
inner join document d on d.id = r.document_id
inner join account a on a.id = r.account_id
inner join account_cee c on c.id = a.account_cee_id
inner join account_cee p1 on p1.id = c.parent_id
inner join account_cee p2 on p2.id = p1.parent_id
inner join account_cee p3 on p3.id = p2.parent_id
where p1.summary = true
and coalesce(p3.node_type, null) not in ('ECONOMICO','PATRIMONIALE')
group by
coalesce(p3.node_type, null),
p2.code, 
p2.description, 
p2.summary, p2.total 
order by p2.code


INSERT INTO bilarow
(
   nodeType,
   lev,
   code,description,
   amountYear,amountYearPre,amountDelta,
   summary,total
)
SELECT  
nodetype,
-lev ,
code, 
description, 
amountYear,
amountYearPre,
amountDelta,
summary, total 
FROM bilarow r
where r.lev > 0
