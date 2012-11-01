module Mprodigy

  class API

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
  alias oldlogin login

  alias oldlogout logout
  
  def self.login(login, password, callback_url)
    RhoLog::info('Mprodigy::SyncEngine::login', login)
    self.oldlogin(login, password, callback_url)
  end

  def self.logout
    RhoLog::info('Mprodigy::SyncEngine::logout', logout)
    self.oldlogout
  end

end