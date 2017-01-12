function house(alpha, iterations)
  %% Initialization
  % clear ; close all; clc
  hold off;
  X = [5;6;7];
  y = [250;290;325];
  m = length(X);
  X0 = X;
  X = [ones(m,1) X];
  theta = normalEqn(X,y);
  J = computeCost(X,y,theta);
  printf('theta using Normal Equation [%.3f;%.3f], cost: %f\n', theta, J);

  % preparing data for ploting
  
  
  
  theta = [0;0];
  [theta, J_history, theta_history] = gradientDescent(X,y,theta, alpha, iterations);
  J = computeCost(X,y,theta);
  printf('theta using gradient descent with alpha(%f) and iterations(%d): [%.3f;%.3f], cost: %f\n', ...
    alpha, iterations, theta, J);
  
  num = 300;
  a = linspace(0, theta(1) * 2, num);
  b = linspace(0, theta(2) * 2, num);
  [aa,bb] = meshgrid(a,b);
  zz = zeros(size(aa));
  for i = 1:num
    for j = 1:num
      theta_temp = [aa(i,j);bb(i,j)];
      zz(i,j) = computeCost(X,y,theta_temp);
    end
  end
  
  mesh(aa,bb,zz);
  
  hold on;
  % step = 500;
  sample = [1:10,10:100:iterations];
  theta0_history = theta_history(1,:)(sample);
  theta1_history = theta_history(2,:)(sample);
  J_history = J_history(sample);
  %plot3(theta0_history,theta1_history,J_history,'marker','x','color','r');
  scatter3(theta0_history,theta1_history,J_history,12,'r','x');
  
end