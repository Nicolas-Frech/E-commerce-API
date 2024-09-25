CREATE TABLE product_user (
    product_id BIGINT NOT NULL,
    user_id BIGINT NOT NULL,
    PRIMARY KEY (product_id ,user_id),
    FOREIGN KEY (product_id) REFERENCES products(id),
    FOREIGN KEY (user_id) REFERENCES users(id)
);