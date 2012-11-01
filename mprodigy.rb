module Mprodigy

class API

  def self.init() {
    # just here for monkey patching
  }

  def self.sessionBegin(applicationId, version, instance, other)
     return Mprodigy::native_sessionBegin(applicationId, version, instance, other)
  end 

  def self.sessionEnd(sessionId)
     return Mprodigy::native_sessionEnd(sessionId)
  end 

  def self.userLogin(sessionId, username)
     return Mprodigy::native_userLogin(sessionId, username)
  end 

  def self.userLogout(userId)
     return Mprodigy::native_userLogout(userId)
  end 
    
end

end

module SyncEngine
  alias self.oldlogin self.login

  alias self.oldlogout self.logout
  
  def self.login(login, password, callback_url)
    RhoLog::info('Mprodigy::SyncEngine::login', login)
    self.oldlogin(login, password, callback_url)
  end

  def self.logout()
    RhoLog::info('Mprodigy::SyncEngine::logout', logout)
    self.oldlogout()
  end

end