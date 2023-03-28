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
## @deftypefn {} {@var{retval} =} MonteCarlo (@var{input1}, @var{input2})
##
## @seealso{}
## @end deftypefn

## Author: Paula <Paula@DESKTOP-QMOV01R>
## Created: 2022-12-13

function I = MonteCarlo (g,a,b,M,nr_sim=1000)
  x = unifrnd(a,b,1,nr_sim);
  y = unifrnd(0,M,1,nr_sim);
  clf;
  hold on;
  plot(x(g(x)>y),y(g(x)>y),'x','MarkerFaceColor','c')
  fplot(g,[a,b])
  I = mean(g(x)>y) * ((b-a)*M); # integral(g, a, b)
  integral(g,a,b)
endfunction
# g = @(x) exp(-x.^2)
# a = -2
# b = 2
# M = 1
