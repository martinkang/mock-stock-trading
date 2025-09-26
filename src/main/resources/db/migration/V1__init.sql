-- 사용자
CREATE TABLE IF NOT EXISTS users (
    id          BIGSERIAL PRIMARY KEY,
    username    VARCHAR(50) NOT NULL UNIQUE,
    balance     NUMERIC(18,2) DEFAULT 0,
    created_at  TIMESTAMP NOT NULL DEFAULT now()
);

-- 주문(모의 매수/매도)
CREATE TABLE IF NOT EXISTS orders (
    id          BIGSERIAL PRIMARY KEY,
    user_id     BIGINT NOT NULL REFERENCES users(id),
    stock_sym   VARCHAR(12) NOT NULL,
    side        VARCHAR(4)  NOT NULL CHECK (side IN ('BUY','SELL')),
    qty         INT NOT NULL CHECK (qty > 0),
    price       NUMERIC(18,2) NOT NULL CHECK (price >= 0),
    status      VARCHAR(12) NOT NULL DEFAULT 'PENDING',
    created_at  TIMESTAMP NOT NULL DEFAULT now()
);

-- 체결(주문이 체결된 기록)
CREATE TABLE IF NOT EXISTS trades (
    id          BIGSERIAL PRIMARY KEY,
    order_id    BIGINT NOT NULL REFERENCES orders(id),
    trade_qty   INT NOT NULL CHECK (trade_qty > 0),
    trade_price NUMERIC(18,2) NOT NULL CHECK (trade_price >= 0),
    traded_at   TIMESTAMP NOT NULL DEFAULT now()
);

-- 조회 최적화를 위한 인덱스 예시
CREATE INDEX IF NOT EXISTS

 idx_orders_user_created ON orders(user_id, created_at DESC);
CREATE INDEX IF NOT EXISTS idx_trades_order       ON trades(order_id);
