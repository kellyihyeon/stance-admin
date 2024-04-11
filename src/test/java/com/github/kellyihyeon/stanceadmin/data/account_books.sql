# DROP -> CREATE
drop table if exists account_books;

create table account_books (
                               id bigint not null auto_increment,
                               transaction_id bigint,
                               transaction_type enum ('DEPOSIT','WITHDRAW'),
                               transaction_date date,
                               summary varchar(255),
                               amount bigint,
                               balance bigint,
                               creator_id bigint,
                               created_date datetime(6),
                               updater_id bigint,
                               updated_date datetime(6),
                               primary key (id)
) engine=InnoDB;

# 초기화 데이터
INSERT INTO account_books(id, summary, transaction_type, amount, balance, creator_id, created_date, updater_id,
                          updated_date, transaction_date, transaction_id)
VALUES (1, '강이현', 'DEPOSIT', 60000, 60000, '1', '2024-02-07 09:06:15.610859', null, null, '2024-02-07', 1);
