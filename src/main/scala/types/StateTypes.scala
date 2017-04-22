package types

import ramdoms.RandomGenerator


object StateTypes {

  type RandomState[+A] = RandomGenerator =>(A,RandomGenerator)

  type State [S,+A] = (S)=>(A,S)

}
