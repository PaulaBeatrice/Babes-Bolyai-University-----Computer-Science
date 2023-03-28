clf; grid on; hold on;
% (i)
N=1000;
zaruri=randi(6,4,N);

sum_zar=sum(zaruri); % suma pe coloana sum(zaruri,1)
% sum(zaruri,2) suma pe linii
% relativ: A/N

h=hist(sum_zar,4:24);
A=[4:24;h]' % legea normala, gauss

% (ii)
xticks(4:24);
xlim([3 25]);
yticks([0:0.01:0.15]); % 0.14
ylim([0 0.15]);
bar(4:24,h/N,'hist','FaceColor','b');

pos=[4:24];
frecv_max=max(h);
suma_max=pos(h==frecv_max) % vector conditie

% (iii)
s_teo=[];
for i1=1:6
  for i2=1:6
    for i3=1:6
      for i4=1:6
        s=i1+i2+i3+i4;
        s_teo=[s_teo s];
      endfor
    endfor
  endfor
endfor

h_teo=hist(s_teo,[4:24]);
bar(4:24,h_teo/N,'FaceColor','y');
set(findobj('type','patch'),'facealpha',0.7);

pos_teo=[4:24];
frecv_max_teo=max(h_teo);
suma_max_teo=pos_teo(h_teo==frecv_max_teo)

% (iv)
% A: sum_zar <= 20
% B: sum_zar >= 10
p_cond_sum = sum((sum_zar >= 10) & (sum_zar <= 20))/sum(sum_zar <= 20) % && lazy, & eager
p_cond_sum_teo = sum((s_teo >= 10) & (s_teo <= 20))/sum(s_teo <= 20)

