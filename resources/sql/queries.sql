-- :name save-book! :! :n
-- :doc creates a new message using the name, message, and timestamp keys
INSERT INTO booksharing
(book, message, timestamp)
VALUES (:book, :message, :timestamp)
-- :name get-messages :? :*
-- :doc selects all available messages
SELECT * from booksharing