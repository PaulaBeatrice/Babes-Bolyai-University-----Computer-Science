clf; grid on; hold on;

p=1/3; n=5; m=2000;

x=binornd(n,p,1,m);
N=hist(x,0:n);
bar(0:n,N/m,'hist','FaceColor','b'); % bars fara spatii intre ele
bar(0:n,binopdf(0:n,n,p),'FaceColor','y');
legend('probabilitatile estimate','probabilitatile teroretice');
set(findobj('type','patch'),'facealpha',0.7);xlim([-1 n+1]); % ylim also

% 1:5
% v=[1 2 1 3 1 4 2 5 1 3 2 3 4];
% h=hist(v,1:5);
% h=4 3 3 2 1

% rnd pdf cdf-cumulative pdf
% binocdf(5,1/3,4) insumam toate pana la 4
