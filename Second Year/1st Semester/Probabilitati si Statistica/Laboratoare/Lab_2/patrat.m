function fr = patrat(N=500)
  clf; hold on; axis equal; grid on;
  rectangle('Position',[0,0,1,1]);
  contor=0;
  C=[0.5 0.5]
  for i =1:N
    x = rand;
    y = rand;
    P = [x y];
    % plot(x,y,'rh'); - Desenare punct
    %i)sunt in interiorul cercului tangent laturilor patratului.
    if pdist([C;P]) < 0.5
      %plot(x,y,'rh');
      %contor++;
    endif
    %ii)sunt mai apropiate de centrul patratului decat de varfurile patratului.
    vf1=[1 0];
    vf2=[1 1];
    vf3=[0 1];
    vf4=[0 0];
    if pdist([C;P]) < pdist([vf1;P]) && pdist([C;P]) < pdist([vf2;P]) && pdist([C;P]) < pdist([vf3;P]) && pdist([C;P]) < pdist([vf4;P])
      plot(x,y,'rh');
      contor++;
    endif
    %iii)formeaza cu varfurile patratului doua triunghiuri ascuţitunghice si doua triunghiuri obtuzunghice.
  endfor
  fr = contor/N;
end
