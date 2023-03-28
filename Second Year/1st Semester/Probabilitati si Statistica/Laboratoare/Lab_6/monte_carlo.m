n = 100; clf; hold on; # curata ecranul
# frecvente absolute
x = normrnd(165,10,1,1000); # medie, persoane, nrlinii, nrcoloane-nr simulari
# hist(x,10);

# frecvente relative
# hist(x, mijloace bare, norm) ; norm - suma inaltimilor barelor(de obicei 1)
[~,mijl_bare] = hist(x,10);
l = (max(x) - min(x)) / 10;
norm = 1/l;
hist(x, mijl_bare, norm); # suma ariilor barelor trebuie sa fie 1
                          # latimea unei bare e (min(x) - max(x))/10
                          #  suma ariilor barelor = suma inaltimilor barelor * latimea unei bare
                          # => norm = 1/L

# lambda function
f = @(t)normpdf(t,165,10)
fplot(f,[min(x),max(x)],'-r', 'LineWidth', 4) # deseneaza cu rosul, grosimea 4
mean(x)
std(x)

mean(160<x & x<170) # median
#P(x<=a)=normcdf(a,medie,sigma)
normcdf(170,165,10)-normcdf(160,165,10)

