INSERT INTO users(username, balance) VALUES
('alice', 1000000), ('bob', 500000)
ON CONFLICT DO NOTHING;

INSERT INTO orders(user_id, stock_sym, side, qty, price, status)
VALUES
(1, 'AAPL', 'BUY', 10, 190.00, 'PENDING'),
(2, 'TSLA', 'SELL', 5,  250.00, 'PENDING')
ON CONFLICT DO NOTHING;;
