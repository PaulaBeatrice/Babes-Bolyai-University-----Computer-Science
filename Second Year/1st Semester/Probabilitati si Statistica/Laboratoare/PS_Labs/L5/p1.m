% Exercitiul 1

clf; grid on; hold on;

N = 1000; % numarul de simulari

v = [0, 1, 2, 3]; % 0 -> 0, 1 -> A, 2 -> B, 3 -> AB
p = [0.46, 0.4, 0.1, 0.04];

x1 = rndvardisc(v, p, N);
x2 = randsample(v, N, replacement = true, p);

rel_frq_x1 = hist(x1, v);
bar(v, rel_frq_x1, 'hist', 'Facecolor', 'b');

rel_frq_x2 = hist(x2, v);
bar(v, rel_frq_x2, 'Facecolor', 'y');

set(findobj('type', 'patch'), 'facealpha', 0.7);
xlim([0 5]); xticks(1 : 4);
