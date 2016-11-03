function [J, grad] = costFunctionReg(theta, X, y, lambda)
%COSTFUNCTIONREG Compute cost and gradient for logistic regression with regularization
%   J = COSTFUNCTIONREG(theta, X, y, lambda) computes the cost of using
%   theta as the parameter for regularized logistic regression and the
%   gradient of the cost w.r.t. to the parameters. 

% Initialize some useful values
m = length(y); % number of training examples
h = X * theta;
s = sigmoid(h);

% You need to return the following variables correctly 
% J = 0;
J = (-y' * log(s) - (1 - y)' * log(1 - s)) / m ...
  + lambda / (2 * m) * (sum(theta .^ 2) - theta(1) ^ 2)  ;


% ====================== YOUR CODE HERE ======================
% Instructions: Compute the cost of a particular choice of theta.
%               You should set J to the cost.
%               Compute the partial derivatives and set grad to the partial
%               derivatives of the cost w.r.t. each parameter in theta
grad = zeros(size(theta));
for i = 1:size(theta)
  Xi = X(:,i);
  grad(i) = (s - y)' * Xi / m;
  if (i > 1)
    grad(i) += lambda * theta(i) / m;
  endif
end





% =============================================================

end
