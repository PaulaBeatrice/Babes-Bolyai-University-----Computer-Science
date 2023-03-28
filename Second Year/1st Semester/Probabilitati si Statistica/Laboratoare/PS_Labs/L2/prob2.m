% a) i)
clf; hold on; axis square; axis off;

N=2000;
rectangle('Position', [0 0 1 1]);

count = 0;
for i=1:N
  x = rand; y = rand;

  distance = pdist([0.5 0.5; x y]);
  if distance < 0.5
    count++;
    plot(x, y, 'dr', 'MarkerSize', 2, 'MarkerFaceColor', 'r');
  endif
endfor

p = count/N

% a) ii)

figure; hold on; axis square; axis off;

N=2500;
rectangle('Position', [0 0 1 1]);

count = 0;
for i=1:N
  x = rand; y = rand;

  OP = pdist([0.5 0.5; x y]);

  AP = pdist([0 1; x y]);
  BP = pdist([1 1; x y]);
  CP = pdist([1 0; x y]);
  DP = pdist([0 0; x y]);

  minim = min([AP BP CP DP]);

  % if OP <= AP && OP <= BP && OP <= CP && OP <= DP
  if OP <= minim
    count++;
    plot(x, y, 'dr', 'MarkerSize', 2, 'MarkerFaceColor', 'r');
  endif
endfor

p = count/N

% a) iii)

figure; hold on; axis square; axis off;

N=2000;
rectangle('Position', [0 0 1 1]);

count = 0;
for i=1:N
  x = rand; y = rand;

  v1 = [0 1] - [x y];
  v2 = [1 1] - [x y];
  v3 = [1 0] - [x y];
  v4 = [0 0] - [x y];

  u1 = sum(v1 .* v2);
  u2 = sum(v2 .* v3);
  u3 = sum(v3 .* v4);
  u4 = sum(v4 .* v1);
  u = [u1 u2 u3 u4];

  count1 = length(find(u < 0));

  if count1 == 2
    count++;
    plot(x, y, 'dr', 'MarkerSize', 2, 'MarkerFaceColor', 'r');
  endif
endfor

p = count/N


