# encoding: utf-8

# minha_app.rb
require 'sinatra'
require 'json'


set :bind, '0.0.0.0'
set :port, 80

users = Hash.new

users["10570835771"] = {name: "Gabriel Duarte", passwd: "123456", coins: 0}
users["11111111111"] = {name: "Leo Nunes", passwd: "123456", coins: 0}


get '/check_user/:cpf/:passwd' do

  user = users[params['cpf']]
  if (!user.nil?) and (params["passwd"] == user[:passwd])
	  JSON(user)
	  #200
  else
  	401
  end
  
end


get '/add_user/:cpf/:passwd/:name' do

  user = users[params['cpf']]
  if (!user.nil?) 
    401
    #200
  else
    users[params['cpf']] = {name: params['name'], passwd: params['passwd'], coins: 0}
    200
  end
  
end




get '/update_coins/:cpf/:passwd/:val' do

  user = users[params['cpf']]
  if (!user.nil?) and (params["passwd"] == user[:passwd])
	  user[:coins] += params["val"].to_i
	  200
  else
  	401
  end
  
end
