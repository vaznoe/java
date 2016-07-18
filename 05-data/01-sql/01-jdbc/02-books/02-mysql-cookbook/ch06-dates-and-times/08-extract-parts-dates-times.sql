
SELECT dt, DATE(dt), TIME(dt) FROM datetime_val;
/*
+---------------------+------------+----------+
| dt                  | DATE(dt)   | TIME(dt) |
+---------------------+------------+----------+
| 1970-01-01 00:00:00 | 1970-01-01 | 00:00:00 |
| 1999-12-31 09:00:00 | 1999-12-31 | 09:00:00 |
| 2000-06-04 15:45:30 | 2000-06-04 | 15:45:30 |
| 2017-03-16 12:30:15 | 2017-03-16 | 12:30:15 |
+---------------------+------------+----------+
4 rows in set (0.00 sec)
*/

SELECT dt, YEAR(dt), DAYOFMONTH(dt), HOUR(dt), SECOND(dt) FROM datetime_val;
/*
+---------------------+----------+----------------+----------+------------+
| dt                  | YEAR(dt) | DAYOFMONTH(dt) | HOUR(dt) | SECOND(dt) |
+---------------------+----------+----------------+----------+------------+
| 1970-01-01 00:00:00 |     1970 |              1 |        0 |          0 |
| 1999-12-31 09:00:00 |     1999 |             31 |        9 |          0 |
| 2000-06-04 15:45:30 |     2000 |              4 |       15 |         30 |
| 2017-03-16 12:30:15 |     2017 |             16 |       12 |         15 |
+---------------------+----------+----------------+----------+------------+
4 rows in set (0.00 sec)
*/

SELECT d, DAYOFYEAR(d) FROM date_val;
/*
+------------+--------------+
| d          | DAYOFYEAR(d) |
+------------+--------------+
| 1864-02-28 |           59 |
| 1900-01-15 |           15 |
| 1999-12-31 |          365 |
| 2000-06-04 |          156 |
| 2017-03-16 |           75 |
+------------+--------------+
5 rows in set (0.00 sec)
*/

SELECT d, DAYNAME(d), LEFT(DAYNAME(d),3) FROM date_val;
/*
+------------+------------+--------------------+
| d          | DAYNAME(d) | LEFT(DAYNAME(d),3) |
+------------+------------+--------------------+
| 1864-02-28 | Sunday     | Sun                |
| 1900-01-15 | Monday     | Mon                |
| 1999-12-31 | Friday     | Fri                |
| 2000-06-04 | Sunday     | Sun                |
| 2017-03-16 | Thursday   | Thu                |
+------------+------------+--------------------+
5 rows in set (0.00 sec)
*/

SELECT d, DAYNAME(d), DAYOFWEEK(d), WEEKDAY(d) FROM date_val;
/*
+------------+------------+--------------+------------+
| d          | DAYNAME(d) | DAYOFWEEK(d) | WEEKDAY(d) |
+------------+------------+--------------+------------+
| 1864-02-28 | Sunday     |            1 |          6 |
| 1900-01-15 | Monday     |            2 |          0 |
| 1999-12-31 | Friday     |            6 |          4 |
| 2000-06-04 | Sunday     |            1 |          6 |
| 2017-03-16 | Thursday   |            5 |          3 |
+------------+------------+--------------+------------+
5 rows in set (0.00 sec)
*/

SELECT dt, EXTRACT(DAY FROM dt), EXTRACT(HOUR FROM dt) FROM datetime_val;
/*
+---------------------+----------------------+-----------------------+
| dt                  | EXTRACT(DAY FROM dt) | EXTRACT(HOUR FROM dt) |
+---------------------+----------------------+-----------------------+
| 1970-01-01 00:00:00 |                    1 |                     0 |
| 1999-12-31 09:00:00 |                   31 |                     9 |
| 2000-06-04 15:45:30 |                    4 |                    15 |
| 2017-03-16 12:30:15 |                   16 |                    12 |
+---------------------+----------------------+-----------------------+
4 rows in set (0.00 sec)
*/

SELECT dt, DATE_FORMAT(dt,'%Y') AS year, DATE_FORMAT(dt,'%d') AS day, TIME_FORMAT(dt,'%H') AS hour, TIME_FORMAT(dt,'%s') AS second FROM datetime_val;
/*
+---------------------+------+------+------+--------+
| dt                  | year | day  | hour | second |
+---------------------+------+------+------+--------+
| 1970-01-01 00:00:00 | 1970 | 01   | 00   | 00     |
| 1999-12-31 09:00:00 | 1999 | 31   | 09   | 00     |
| 2000-06-04 15:45:30 | 2000 | 04   | 15   | 30     |
| 2017-03-16 12:30:15 | 2017 | 16   | 12   | 15     |
+---------------------+------+------+------+--------+
4 rows in set (0.00 sec)
*/

SELECT dt, DATE_FORMAT(dt,'%Y-%m-%d') AS 'date part', TIME_FORMAT(dt,'%T') AS 'time part' FROM datetime_val;
/*
+---------------------+------------+-----------+
| dt                  | date part  | time part |
+---------------------+------------+-----------+
| 1970-01-01 00:00:00 | 1970-01-01 | 00:00:00  |
| 1999-12-31 09:00:00 | 1999-12-31 | 09:00:00  |
| 2000-06-04 15:45:30 | 2000-06-04 | 15:45:30  |
| 2017-03-16 12:30:15 | 2017-03-16 | 12:30:15  |
+---------------------+------------+-----------+
4 rows in set (0.00 sec)
*/

SELECT dt, DATE_FORMAT(dt,'%M %e, %Y') AS 'descriptive date', TIME_FORMAT(dt,'%H:%i') AS 'hours/minutes' FROM datetime_val;
/*
+---------------------+-------------------+---------------+
| dt                  | descriptive date  | hours/minutes |
+---------------------+-------------------+---------------+
| 1970-01-01 00:00:00 | January 1, 1970   | 00:00         |
| 1999-12-31 09:00:00 | December 31, 1999 | 09:00         |
| 2000-06-04 15:45:30 | June 4, 2000      | 15:45         |
| 2017-03-16 12:30:15 | March 16, 2017    | 12:30         |
+---------------------+-------------------+---------------+
4 rows in set (0.00 sec)
*/

