
SELECT t, srcuser, FLOOR((size+1023)/1024) FROM mail where size > 50000 ORDER BY FLOOR((size+1023)/1024);
/*
+---------------------+---------+-------------------------+
| t                   | srcuser | FLOOR((size+1023)/1024) |
+---------------------+---------+-------------------------+
| 2014-05-11 10:15:08 | barb    |                      57 |
| 2014-05-14 14:42:21 | barb    |                      96 |
| 2014-05-12 12:48:13 | tricia  |                     191 |
| 2014-05-15 10:25:52 | gene    |                     976 |
| 2014-05-14 17:03:01 | tricia  |                    2339 |
+---------------------+---------+-------------------------+
5 rows in set (0.00 sec)
*/

SELECT t, srcuser, FLOOR((size+1023)/1024) AS kilobytes FROM mail WHERE size > 50000 ORDER BY kilobytes;
/*
+---------------------+---------+-----------+
| t                   | srcuser | kilobytes |
+---------------------+---------+-----------+
| 2014-05-11 10:15:08 | barb    |        57 |
| 2014-05-14 14:42:21 | barb    |        96 |
| 2014-05-12 12:48:13 | tricia  |       191 |
| 2014-05-15 10:25:52 | gene    |       976 |
| 2014-05-14 17:03:01 | tricia  |      2339 |
+---------------------+---------+-----------+
5 rows in set (0.00 sec)
*/

