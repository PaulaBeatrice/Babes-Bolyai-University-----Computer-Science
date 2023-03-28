% 3 a
m = 1000;
p = sum(hygepdf(3:6,49,6,6))
x = geornd(p,1,m);

% 3 b
val = mean(x >= 10); % fav/pos !!! util pentru estimari: mean + vector conditie
fprintf("Prob estimata: %2.7f\n", val); % 0.818
Tval = 1 - sum(geopdf(0:9,p)) % 0.8285

