# drop -> create
drop table if exists deposits;

create table deposits (
                          id bigint not null auto_increment,
                          member_id bigint,
                          category enum ('MEMBERSHIP_FEE','TEAM_PARTY_EXPENSE','UNIFORM_EXPENSE','INTEREST','CASHBACK'),
                          depositor varchar(255),
                          amount bigint,
                          member_type enum ('ACTIVE','INACTIVE','GUEST'),
                          membership_fee_type enum ('REGULAR','LESSON_ONE_DAY','ONE_DAY'),
                          due_month enum ('JANUARY','FEBRUARY','MARCH','APRIL','MAY','JUNE','JULY','AUGUST','SEPTEMBER','OCTOBER','NOVEMBER','DECEMBER'),
                          deposit_date date,
                          description varchar(255),
                          creator_id bigint,
                          created_date datetime(6),
                          updater_id bigint,
                          updated_date datetime(6),
                          primary key (id)
) engine=InnoDB;


# 초기화 데이터
INSERT INTO deposits(id, amount, category, created_date, creator_id, deposit_date, depositor, description, due_month,
                     member_id, member_type, membership_fee_type, updated_date, updater_id)
VALUES (1, 60000, 'MEMBERSHIP_FEE', '2024-02-07 09:06:15.610859', 1, '2024-02-07', '강이현', '3월 회비 입금', 'MARCH',
        1, 'ACTIVE', 'REGULAR', null, null);
