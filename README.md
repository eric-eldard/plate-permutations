Iron is expensive. When my new bar came with 10, 25, and 45 pound plates, I realized there'd be a lot of unavailable
weight increments—not ideal for progressive training.

```
Using default plate set because no values were passed as args:
[10.0, 25.0, 45.0]

45  :                        (+ 45)
65  :   10s                  (+ 45)
95  :          25s           (+ 45)
115 :   10s    25s           (+ 45)
135 :                 45s    (+ 45)
155 :   10s           45s    (+ 45)
185 :          25s    45s    (+ 45)
205 :   10s    25s    45s    (+ 45)

Unreachable weights: (using increment 20) [85, 105, 125, 145, 165] and weights > 205
```

Should I be considering the set with 15 and 35 pound plates for $250 more? How far would an extra pair of 10s get me?
After running a few combinations of plates through this app, I found that a 6-pack of 2.5lb plates and a pair of 5 
pounders would fill in every 5lb increment from bar weight to 230lb for a total of forty bucks. Programming FTW!

```
Plate weights passed as args:
[2.5, 2.5, 2.5, 5.0, 10.0, 25.0, 45.0]

45  :                                                    (+ 45)
50  :   2.5s                                             (+ 45)
55  :   2.5s   2.5s                                      (+ 45)
55  :                        5s                          (+ 45)
60  :   2.5s   2.5s   2.5s                               (+ 45)
60  :   2.5s                 5s                          (+ 45)
65  :   2.5s   2.5s          5s                          (+ 45)
65  :                               10s                  (+ 45)
70  :   2.5s   2.5s   2.5s   5s                          (+ 45)
70  :   2.5s                        10s                  (+ 45)
75  :   2.5s   2.5s                 10s                  (+ 45)
75  :                        5s     10s                  (+ 45)
80  :   2.5s   2.5s   2.5s          10s                  (+ 45)
80  :   2.5s                 5s     10s                  (+ 45)
85  :   2.5s   2.5s          5s     10s                  (+ 45)
90  :   2.5s   2.5s   2.5s   5s     10s                  (+ 45)
95  :                                      25s           (+ 45)
100 :   2.5s                               25s           (+ 45)
105 :   2.5s   2.5s                        25s           (+ 45)
105 :                        5s            25s           (+ 45)
110 :   2.5s   2.5s   2.5s                 25s           (+ 45)
110 :   2.5s                 5s            25s           (+ 45)
115 :   2.5s   2.5s          5s            25s           (+ 45)
115 :                               10s    25s           (+ 45)
120 :   2.5s   2.5s   2.5s   5s            25s           (+ 45)
120 :   2.5s                        10s    25s           (+ 45)
125 :   2.5s   2.5s                 10s    25s           (+ 45)
125 :                        5s     10s    25s           (+ 45)
130 :   2.5s   2.5s   2.5s          10s    25s           (+ 45)
130 :   2.5s                 5s     10s    25s           (+ 45)
135 :   2.5s   2.5s          5s     10s    25s           (+ 45)
135 :                                             45s    (+ 45)
140 :   2.5s   2.5s   2.5s   5s     10s    25s           (+ 45)
140 :   2.5s                                      45s    (+ 45)
145 :   2.5s   2.5s                               45s    (+ 45)
145 :                        5s                   45s    (+ 45)
150 :   2.5s   2.5s   2.5s                        45s    (+ 45)
150 :   2.5s                 5s                   45s    (+ 45)
155 :   2.5s   2.5s          5s                   45s    (+ 45)
155 :                               10s           45s    (+ 45)
160 :   2.5s   2.5s   2.5s   5s                   45s    (+ 45)
160 :   2.5s                        10s           45s    (+ 45)
165 :   2.5s   2.5s                 10s           45s    (+ 45)
165 :                        5s     10s           45s    (+ 45)
170 :   2.5s   2.5s   2.5s          10s           45s    (+ 45)
170 :   2.5s                 5s     10s           45s    (+ 45)
175 :   2.5s   2.5s          5s     10s           45s    (+ 45)
180 :   2.5s   2.5s   2.5s   5s     10s           45s    (+ 45)
185 :                                      25s    45s    (+ 45)
190 :   2.5s                               25s    45s    (+ 45)
195 :   2.5s   2.5s                        25s    45s    (+ 45)
195 :                        5s            25s    45s    (+ 45)
200 :   2.5s   2.5s   2.5s                 25s    45s    (+ 45)
200 :   2.5s                 5s            25s    45s    (+ 45)
205 :   2.5s   2.5s          5s            25s    45s    (+ 45)
205 :                               10s    25s    45s    (+ 45)
210 :   2.5s   2.5s   2.5s   5s            25s    45s    (+ 45)
210 :   2.5s                        10s    25s    45s    (+ 45)
215 :   2.5s   2.5s                 10s    25s    45s    (+ 45)
215 :                        5s     10s    25s    45s    (+ 45)
220 :   2.5s   2.5s   2.5s          10s    25s    45s    (+ 45)
220 :   2.5s                 5s     10s    25s    45s    (+ 45)
225 :   2.5s   2.5s          5s     10s    25s    45s    (+ 45)
230 :   2.5s   2.5s   2.5s   5s     10s    25s    45s    (+ 45)

Unreachable weights: (using increment 5) weights > 230
```