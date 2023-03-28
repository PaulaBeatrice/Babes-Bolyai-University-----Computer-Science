fileID = fopen('keywords_ham.txt','r');
ham=textscan(fileID,'%s');
fclose(fileID);

fileID = fopen('keywords_spam.txt','r');
spam=textscan(fileID,'%s');
fclose(fileID);

fileID = fopen('email1.txt','r');
email=textscan(fileID,'%s');
fclose(fileID);


words=unique([ham{1};spam{1}]);
fprintf(' %s',words{:}); fprintf('.\n');
fprintf('Numarul de cuvinte spam: %d.\n',numel(spam{1}));
fprintf('Numarul de cuvinte ham: %d.\n',numel(ham{1}));
nr_spam = numel(spam{1});
nr_ham = numel(ham{1});
nr_cuvinte = nr_spam + nr_ham;
p_spam = nr_spam/nr_cuvinte;
p_ham = nr_ham/nr_cuvinte;
fprintf('Numarul de cuvinte total: %d.\n',nr_cuvinte);
fprintf('Probabilitate ham: %d.\n', p_ham);
fprintf('Probabilitate spam: %d.\n', p_spam);


for i=1:numel(words)
  if ismember(words{i},email{1})
    p_spam *= sum(strcmp(words{i},spam{1})) / nr_spam;
    p_ham *= sum(strcmp(words{i},ham{1})) / nr_ham;
  else
    p_spam *= 1-sum(strcmp(words{i},spam{1})) / nr_spam;
    p_ham *= 1-sum(strcmp(words{i},ham{1})) / nr_ham;
  endif
endfor

fprintf('Probabilitate ca email ul sa fie ham: %d.\n', p_ham);
fprintf('Probabilitate ca email ul sa fie spam: %d.\n', p_spam);

if p_spam>p_ham
  fprintf('Email ul e spam\n')
 else
  fprintf('Email ul e ham\n')
 endif

