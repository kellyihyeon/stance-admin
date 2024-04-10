# 초기화 데이터
INSERT INTO account_books(id, summary, transaction_type, amount, balance, creator_id, created_date, updater_id,
                          updated_date)
VALUES (1, '강이현', 'DEPOSIT', 60000, 60000, '1', '2024-02-07 09:06:15.610859', null, null);


INSERT INTO deposits(id, amount, category, created_date, creator_id, deposit_date, depositor, description, due_month,
                     member_id, member_type, membership_fee_type, updated_date, updater_id)
VALUES (1, 60000, 'MEMBERSHIP_FEE', '2024-02-07 09:06:15.610859', 1, '2024-02-07', '강이현', '3월 회비 입금', 'MARCH',
        1, 'ACTIVE', 'REGULAR', null, null);