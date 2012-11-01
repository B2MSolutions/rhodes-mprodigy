module Mprodigy

  class API

    # def self.instrument
    #   # just here for monkey patching
    #   RhoLog::info('Mprodigy::API', 'instrument')
    #   return
    # end

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

# monkey patching sync engine to instrument login and logout
module SyncEngine
  class << self
    alias_method :orig_login, :login
    alias_method :orig_logout, :logout
  end
  
  def self.login(user, password, callback_url)
    RhoLog::info('Mprodigy::SyncEngine', 'login')
    orig_login(user, password, callback_url)
  end

  def self.logout
    RhoLog::info('Mprodigy::SyncEngine::logout', 'logout')
    orig_logout
  end

end