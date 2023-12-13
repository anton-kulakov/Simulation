package com.anton_kulakov.action;

import com.anton_kulakov.World;

public class PrintStartInfoAction extends Action {
    @Override
    public void doAction(World world, World copyWorld) {
        System.out.println();
        System.out.println(
                """
                                Старт пандемии коронавируса в конце 2019 года, спад экономики в ряде лидирующих стран мира,\s
                                а также разгоревшиеся военные конфликты в разных уголках планеты привели к серьезному сокращению\s
                                венчурных инвестиций в стартапы.
                         \s
                                В таких условиях большое число компаний в сфере IT были вынуждены сократить издержки. И как следствие, тысячи\s
                                специалистов лишились работы.
                         \s
                                У вас есть возможность запустить симуляцию, которая отражает реальность рынка IT-технологий на момент \s
                                2023 года. 
                              
                                Компании (\uD83D\uDC68\u200D\uD83D\uDCBC) пытаются как можно быстрее найти деньги венчурных фондов (\uD83D\uDCB0). 
                                Молодые программисты (\uD83D\uDC76) хотят найти работу, пробиваются на собеседования в компании и тем самым тратят их время и ресурсы. \s
                                Школы программирования (\uD83C\uDF93) работают над тем, чтобы получить у джуниоров контакты друзей и родственников с целью продажи своих курсов.
                                Это в итоге приводит к еще большему увеличению молодых специалистов на рынке и росту конкуренции.
                        """
        );
    }
}
