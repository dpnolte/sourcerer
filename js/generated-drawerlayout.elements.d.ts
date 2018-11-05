/* generated @ 2018-11-05T13:36:03.897 */
import "./base.elements";
import "./generated-drawerlayout.attributes";
declare module "LayoutTypes" {
  interface NormalizedDrawerLayoutElement<
    LayoutAttributes={}
  > extends NormalizedViewGroupBase<DrawerLayoutAttributes, LayoutAttributes> {
    type: "drawerLayout";
  }
  interface DenormalizedDrawerLayoutElement<
    LayoutAttributes={},
    ChildrenViewAttributes={}
  > extends DenormalizedViewGroupBase<DrawerLayoutAttributes, LayoutAttributes, ChildrenViewAttributes, DrawerLayoutLayoutParamsAttributes> {
    type: "drawerLayout";
  }}
