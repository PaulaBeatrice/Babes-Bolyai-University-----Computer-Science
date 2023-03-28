fileID = fopen('keywords_ham.txt','r');
ham=textscan(fileID,'%s');
fclose(fileID);

fileID = fopen('keywords_spam.txt','r');
spam=textscan(fileID,'%s');
fclose(fileID);

fileID = fopen('email1.txt','r');
email=textscan(fileID,'%s');
fclose(fileID);

words=unique([ham{1};spam{1}])
fprintf(' %s',words{:}); fprintf('.\n');

%numel returnează numărul de elemente ale unui vector
fprintf('Numarul de cuvinte spam: %d.\n',numel(spam{1}));
fprintf('Numarul de cuvinte ham: %d.\n',numel(ham{1}));
nr_spam = numel(spam{1});
nr_ham = numel(ham{1});
nr_cuvinte = nr_spam + nr_ham;
fprintf('Numarul de cuvinte total: %d.\n',nr_cuvinte);
fprintf('Probabilitate ham: %d.\n',nr_ham/nr_cuvinte);
fprintf('Probabilitate spam: %d.\n',nr_spam/nr_cuvinte);

p_spam = nr_spam/nr_cuvinte;
p_ham = nr_ham/nr_cuvinte;

for i=1:numel(words)
  if ismember(words{i},email{1}) %verif daca un cuvant face parte dintr-un vector de cuvinte
    p_spam *= sum(strcmp(words{i},spam{1}))/nr_spam;
    p_ham *= sum(strcmp(words{i},ham{1}))/nr_ham;
  else
    p_spam *= 1-sum(strcmp(words{i},spam{1}))/nr_spam;
    p_ham *= 1-sum(strcmp(words{i},ham{1}))/nr_ham;
  endif
endfor
%1=================
fprintf('EX 1, LAB 4\n');
fprintf('Probabilitate spam 2: %d.\n',p_spam);
fprintf('Probabilitate ham 2: %d.\n',p_ham);

%2a================
fprintf('EX 2a, LAB 4\n');
k=5;
s=0;
p=0.5;
for i=1:k
  step = binornd(1,p);
  if step == 1
    s = s+1;
    fprintf('Succes: %d\n', s);
  else s = s-1;
    fprintf('Esec: %d\n', s);
  endif
endfor
fprintf('Poz final: %d\n', s);

%2b==================
fprintf('EX 2b, LAB 4\n');
p=0.6;n=5;m=5000;
clf; grid on; hold on;
x=2*binornd(n,p,1,m)-n;
 N=hist(x,-n:n);
 bar(-n:n,N/m,'hist','FaceColor','b');
 poz = 2*(0:n)-n;
 bar(poz,binopdf(0:n,n,p),'FaceColor','y');
 legend('probabilitatile estimate','probabilitatile teroretice');
  set(findobj('type','patch'),'facealpha',0.7);
  xlim([-n-1 n+1]);

