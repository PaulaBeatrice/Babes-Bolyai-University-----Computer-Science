## Copyright (C) 2022 Paula
##
## This program is free software: you can redistribute it and/or modify
## it under the terms of the GNU General Public License as published by
## the Free Software Foundation, either version 3 of the License, or
## (at your option) any later version.
##
## This program is distributed in the hope that it will be useful,
## but WITHOUT ANY WARRANTY; without even the implied warranty of
## MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
## GNU General Public License for more details.
##
## You should have received a copy of the GNU General Public License
## along with this program.  If not, see <https://www.gnu.org/licenses/>.

## -*- texinfo -*-
## @deftypefn {} {@var{retval} =} lab3_2a (@var{input1}, @var{input2})
##
## @seealso{}
## @end deftypefn

## Author: Paula <Paula@DESKTOP-QMOV01R>
## Created: 2022-11-01

function retval = lab3_2a ()
    clf; grid on; hold on;  #albastru - cele rezultate din simulari
    p=0.4; n=8; m=1000;
    x=binornd(n,p,1,m); # cu cat m e mai mare cu atat sunt mai apropiate cele albastre de cele galbene
    N=hist(x,0:n);
    bar(0:n,N/m,'hist','FaceColor','b');
    bar(0:n,binopdf(0:n,n,p),'FaceColor','y');
    legend('probabilitatile estimate','probabilitatile teoretice');
    set(findobj('type','patch'),'facealpha',0.7);xlim([-1 n+1]);
endfunction
