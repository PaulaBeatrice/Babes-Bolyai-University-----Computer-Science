% P(W1=true|C=spam)=1-P(W1=false|C=spam)
% keywordsSpam -> unique -> freq in keywordsSpam
aux=fileread('email1.txt');
oneEmail=strsplit(aux,' ');
LE1=length(oneEmail);

aux=fileread('email2.txt');
twoEmail=strsplit(aux,' ');
LE2=length(twoEmail);

email=twoEmail; % modificam aici pentru al doilea
emailLength=LE2;


aux=fileread('keywords_spam.txt');
keySpam=strsplit(aux,' ');
uKeySpam=unique(keySpam);
uKeySpam=uKeySpam(2:end);

spamFrq=[];
sL=length(keySpam);
sLu=length(uKeySpam);
for i=1:sLu
  counter=0;
  for j=1:sL
    counter += strcmp(uKeySpam(i),keySpam(j));
  endfor

  spamFrq=[spamFrq, counter/sL];
endfor

aux=fileread('keywords_ham.txt');
keyHam=strsplit(aux,' ');
uKeyHam=unique(keyHam);
uKeyHam=uKeyHam(2:end);

hamFrq=[];
hL=length(keyHam);
hLu=length(uKeyHam);
for i=1:hLu
  counter=0;
  for j=1:hL
    counter += strcmp(uKeyHam(i),keyHam(j));
  endfor

  hamFrq=[hamFrq, counter/hL];
endfor


for i=1:sLu
  counter=0;
  for j=1:emailLength
    counter += strcmp(uKeySpam(i),email(j));
  endfor
  if counter == 0
    spamFrq(i) = 1 - spamFrq(i);
  endif
endfor

for i=1:hLu
  counter=0;
  for j=1:emailLength
    counter += strcmp(uKeyHam(i),email(j));
  endfor
  if counter == 0
    hamFrq(i) = 1 - hamFrq(i);
  endif
endfor


Pspam=prod(spamFrq) * sL / (sL + hL)
Pham=prod(hamFrq) * hL / (sL + hL)

