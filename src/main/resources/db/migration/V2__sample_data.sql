INSERT INTO users (username, balance) VALUES
('alice', 10000000),
('bob', 8000000),
('charlie', 12000000),
('david', 9500000),
('emma', 7000000),
('frank', 11000000),
('grace', 13000000),
('henry', 9000000),
('irene', 10500000),
('jack', 8500000)
ON CONFLICT DO NOTHING;

INSERT INTO stocks (symbol, name, price) VALUES
('AAPL', 'Apple Inc.', 180.00),
('TSLA', 'Tesla Inc.', 250.00),
('GOOG', 'Alphabet Inc.', 2700.00),
('AMZN', 'Amazon.com Inc.', 3300.00),
('MSFT', 'Microsoft Corp.', 310.00),
('NVDA', 'NVIDIA Corp.', 440.00),
('META', 'Meta Platforms Inc.', 340.00),
('NFLX', 'Netflix Inc.', 400.00),
('AMD', 'Advanced Micro Devices Inc.', 120.00),
('INTC', 'Intel Corp.', 45.00)
ON CONFLICT DO NOTHING;